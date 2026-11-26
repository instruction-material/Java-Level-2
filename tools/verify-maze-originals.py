"""Verify the legacy reader and untimed runner with bounded Java 21 cases."""

import os
from pathlib import Path
import subprocess
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"

with TemporaryDirectory(prefix="java2-untimed-maze-") as workspace:
    work = Path(workspace)
    fixtures = {
        "short.txt": "####\n#PE#\n####\n",
        "no-start.txt": "###\n#E#\n###\n",
        "no-exit.txt": "###\n#P#\n###\n",
        "short-row.txt": "####\n#P#\n####\n",
    }
    for name, contents in fixtures.items():
        (work / name).write_text(contents, encoding="utf-8")
    for pack in ("Maze-Reader", "Maze-Runner-Part-2"):
        folder = ROOT / pack
        output = work / pack
        output.mkdir()
        sources = sorted((folder / "src/main/java").glob("*.java"))
        subprocess.run(
            [JAVA / "javac", "--release", "21", "-Xlint:all", "-Werror", "-d", output, *sources],
            check=True, cwd=folder, timeout=25,
        )

        def run(name, commands=""):
            result = subprocess.run(
                [JAVA / "java", "-cp", output, "Main", name],
                input=commands, text=True, capture_output=True, cwd=folder, check=True, timeout=4,
            )
            assert result.stderr == "", result
            return result.stdout

        for name, diagnostic in (
            ("missing.txt", "Cannot read maze file."),
            ("no-start.txt", "Maze needs one player start and one exit."),
            ("no-exit.txt", "Maze needs one player start and one exit."),
            ("short-row.txt", "Maze rows must have the same width."),
        ):
            assert run(work / name).strip() == diagnostic, (pack, name)
        for name in ("easy.txt", "medium.txt", "hard.txt"):
            expected = "Start: (1, 1)" if pack == "Maze-Reader" else "Input ended."
            assert expected in run(name), (pack, name)
        if pack == "Maze-Runner-Part-2":
            play = run(work / "short.txt", "w\nd\n")
            assert "Invalid move. Try again." in play and "You've reached the exit!" in play
            assert "Game ended." in run(work / "short.txt", "q\n")
        else:
            assert run(work / "short.txt") == fixtures["short.txt"] + "Start: (1, 1)\n"
        print(f"Verified original {pack} inputs and exit behavior", flush=True)

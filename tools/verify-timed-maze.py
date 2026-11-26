"""Check the timed maze at the CLI and assert that every tested exit joins its worker."""

import os
from pathlib import Path
import subprocess
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1] / "Maze-Runner-Part-3"
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"

with TemporaryDirectory(prefix="java2-timed-maze-") as workspace:
    work = Path(workspace)
    sources = sorted((ROOT / "src/main/java").glob("*.java"))
    subprocess.run(
        [JAVA / "javac", "--release", "21", "-Xlint:all", "-Werror", "-d", work, *sources,
         ROOT / "src/test/java/ContractTests.java"],
        check=True, cwd=ROOT, timeout=25,
    )
    good = work / "short.txt"
    good.write_text("####\n#PE#\n####\n")
    malformed = work / "bad.txt"
    malformed.write_text("####\n#P#\n####\n")
    result = subprocess.run(
        [JAVA / "java", "-cp", work, "ContractTests", good, malformed, work / "missing.txt"],
        check=True, text=True, capture_output=True, cwd=ROOT, timeout=10,
    )
    assert result.stdout.strip() == "Timed maze exit and cleanup checks passed", result
    for board in ("easy.txt", "medium.txt", "hard.txt"):
        for commands, expected in (("", "Input ended."), ("Q\n", "Game ended.")):
            result = subprocess.run(
                [JAVA / "java", "-cp", work, "Main", board], input=commands,
                check=True, text=True, capture_output=True, cwd=ROOT, timeout=4,
            )
            assert expected in result.stdout and result.stderr == "", result
print("Verified all three supplied boards, four game exits, owner interruption, and malformed inputs.")

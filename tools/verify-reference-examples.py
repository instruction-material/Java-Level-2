"""Compile and run the original Java Level 2 reference examples."""

import os
from pathlib import Path
import shutil
import subprocess
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"


def run(source_path, expected_output, text="", fixtures=(), written_output=None):
    source = ROOT / source_path
    with TemporaryDirectory(prefix="java2-reference-") as directory:
        work = Path(directory)
        for fixture in fixtures:
            shutil.copyfile(source.parent / fixture, work / fixture)
        subprocess.run(
            [JAVA / "javac", "--release", "21", "-Xlint:all", "-Werror", "-d", work, source],
            check=True,
            timeout=20,
        )
        result = subprocess.run(
            [JAVA / "java", "-cp", work, "Main"],
            input=text,
            encoding="utf-8",
            cwd=work,
            capture_output=True,
            check=True,
            timeout=10,
        )
        assert result.stdout.splitlines() == expected_output, result.stdout
        assert not result.stderr, result.stderr
        if written_output:
            assert (work / "output.out").read_bytes() == written_output
        print(f"Verified {source_path}", flush=True)


if __name__ == "__main__":
    run(
        "JM-Java-File-IO/Main.java",
        ["Numbers: [12, -3, 0, 7]", "Rows: [[1, 2, 3], [5, 6, 7], [7, 8, 9]]"],
        fixtures=("in1.in", "in2.in"),
        written_output=b"test\n",
    )
    run("JM-stdinstdout/Main.java", ["café and red fox"], "café and red fox\n")
    run("JM-stdinstdout/Main.java", [])
    run(
        "JM6-Try-Catch-Example/src/main/java/Main.java",
        ["10 / 2 = 5", "Cannot divide by zero.", "Both examples completed."],
    )

"""Exercise all five maze workbooks on Java 21."""

import json
import os
from pathlib import Path
import subprocess
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"
WORKBOOKS = json.loads((ROOT / "tools/jm9-course-contracts.json").read_text(encoding="utf-8"))


def compile_sources(folder, output, names):
    subprocess.run(
        [JAVA / "javac", "--release", "21", "-encoding", "UTF-8", "-Xlint:all", "-Werror", "-d", output, *names],
        cwd=folder, check=True, timeout=25,
    )


def run_class(folder, output, name, text=""):
    return subprocess.run(
        [JAVA / "java", "-cp", output, name],
        cwd=folder, input=text, text=True, capture_output=True, timeout=10,
    )


def verify_role(folder, output, workbook, role):
    files = workbook["files"]
    assert {path.relative_to(folder).as_posix() for path in folder.rglob("*") if path.is_file()} == set(files)
    compile_sources(folder, output, [name for name in files if name.endswith(".java")])
    result = run_class(folder, output, "Main", workbook["input"])
    expected = workbook["demo"] if role == "solution" else workbook["starterDemo"]
    actual = result.stdout.splitlines()
    assert result.returncode == 0 and not result.stderr, result
    if "Timer.java" in files:
        # Native startup may pause before the first console write.
        prefix = "Time left: "
        times = [line for line in actual if line.startswith(prefix)]
        assert len(times) == 1 and times[0].endswith(" seconds"), result
        seconds = int(times[0].removeprefix(prefix).removesuffix(" seconds"))
        assert 0 < seconds <= 30, result
        actual = ["Time left: 30 seconds" if line.startswith(prefix) else line for line in actual]
    assert actual == expected, result
    result = run_class(folder, output, "ContractTests")
    if role == "starter":
        assert result.returncode != 0 and f"AssertionError: {workbook['firstFailure']}" in result.stderr, result
    else:
        assert result.returncode == 0 and result.stdout.strip() == workbook["checkLabel"], result


if __name__ == "__main__":
    version = subprocess.check_output([JAVA / "javac", "-version"], text=True).strip()
    assert version == "javac 21" or version.startswith("javac 21."), version
    for folder, workbook in WORKBOOKS.items():
        root = ROOT / folder
        for role in ("starter", "solution"):
            with TemporaryDirectory(prefix="java2-maze-role-") as output:
                verify_role(root / role, output, workbook, role)
        print(f"Verified both roles for {folder}", flush=True)

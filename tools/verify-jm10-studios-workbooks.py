"""Exercise the capstone and six distinct practice workbooks on Java 21."""

import argparse
import json
import os
from pathlib import Path
import subprocess
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"
WORKBOOKS = json.loads((ROOT / "tools/jm10-studios-course-contracts.json").read_text(encoding="utf-8"))


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
    assert result.returncode == 0 and result.stdout.splitlines() == expected, result
    result = run_class(folder, output, "ContractTests")
    if role == "starter":
        assert result.returncode != 0 and f"AssertionError: {workbook['firstFailure']}" in result.stderr, result
    else:
        assert result.returncode == 0 and result.stdout.strip() == workbook["checkLabel"], result


if __name__ == "__main__":
    version = subprocess.check_output([JAVA / "javac", "-version"], text=True).strip()
    assert version == "javac 21" or version.startswith("javac 21."), version
    parser = argparse.ArgumentParser(description="Run starter and solution contracts on Java 21.")
    parser.add_argument("--fixture", type=Path, help="Select another workbook contract file.")
    args = parser.parse_args()
    workbooks = json.loads(args.fixture.read_text(encoding="utf-8")) if args.fixture else WORKBOOKS
    for folder, workbook in workbooks.items():
        root = ROOT / folder
        for role in ("starter", "solution"):
            with TemporaryDirectory(prefix="java2-capstone-role-") as output:
                verify_role(root / role, output, workbook, role)
        print(f"Verified both roles for {folder}", flush=True)

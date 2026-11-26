"""Verify the standalone starter and reference roles with Java 21."""

import os
import subprocess
from pathlib import Path
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"

WORKBOOKS = (
    (
        "JM1-Dog-Class",
        "Dog",
        "constructor initializes Fido's age",
        "0\nDog summary pending\n0",
        "4\nName: Fido, Age: 5, Weight: 15.5, Alive: true\n2",
    ),
    (
        "JM1-Supplemental-Project-1-Employee",
        "Employee",
        "position changes on Jacky",
        "Employee summary pending\nEmployee summary pending",
        "Name: Jacky, Position: Engineer, Years worked: 1, ID Number: 556614\n"
        "Name: Tina, Position: Manager, Years worked: 4, ID Number: 76544",
    ),
)


def run(command, folder):
    return subprocess.run(command, cwd=folder, capture_output=True, text=True, timeout=20)


def compile_java(folder, out):
    sources = sorted(folder.glob("*.java"))
    assert sources, folder
    result = run([JAVA / "javac", "--release", "21", "-Xlint:all", "-Werror", "-d", out, *sources], folder)
    assert result.returncode == 0, result.stderr


def run_class(folder, out, entry):
    return run([JAVA / "java", "-cp", out, entry], folder)


if __name__ == "__main__":
    version = subprocess.check_output([JAVA / "javac", "-version"], text=True).strip()
    assert version == "javac 21" or version.startswith("javac 21."), version
    print(version, flush=True)
    for pack, model, first_failure, starter_output, reference_output in WORKBOOKS:
        for role, expected in (("starter", starter_output), ("solution", reference_output)):
            folder = ROOT / pack / role
            assert {item.name for item in folder.iterdir()} == {
                "Main.java", f"{model}.java", "ContractTests.java", "README.md"
            }
            with TemporaryDirectory(prefix="jm1-workbooks-") as temporary:
                compile_java(folder, temporary)
                demo = run_class(folder, temporary, "Main")
                assert demo.returncode == 0 and demo.stdout.strip() == expected, demo
                checks = run_class(folder, temporary, "ContractTests")
                if role == "starter":
                    assert checks.returncode != 0 and f"AssertionError: {first_failure}" in checks.stderr, checks
                else:
                    assert checks.returncode == 0 and checks.stdout.strip() == f"{model} class checks passed", checks
            print(f"Verified {pack}/{role}", flush=True)

    employee = ROOT / "JM1-Supplemental-Project-1-Employee"
    with TemporaryDirectory(prefix="jm1-original-employee-") as temporary:
        compile_java(employee, temporary)
        demo = run_class(employee, temporary, "Main")
        assert demo.returncode == 0 and "Years worked: 3, ID Number: 76544" in demo.stdout, demo

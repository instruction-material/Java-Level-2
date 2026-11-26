"""Verify the Check-In 1 projects on Java 21."""

import os
import subprocess
from pathlib import Path
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"

WORKBOOKS = {
    "J2-02-check-in-1-supplemental-2": (
        ("Main.java", "Ticket.java", "ContractTests.java", "README.md"),
        "first use succeeds",
        ("Ticket numbers: 1, 2", "First redemption: false", "Repeated redemption: false",
         "Second ticket redeemed: false", "Issued: 2, redeemed: 0"),
        ("Ticket numbers: 1, 2", "First redemption: true", "Repeated redemption: false",
         "Second ticket redeemed: false", "Issued: 2, redeemed: 1"),
        "Ticket practice checks passed",
    ),
    "J2-03-check-in-1-supplemental-3": (
        ("Main.java", "Attempt.java", "ContractTests.java", "README.md"),
        "points view uses descending points then ascending time",
        ("Points view: [North, East, West, South]", "Time view: [North, East, West, South]",
         "Original: [North, East, West, South]"),
        ("Points view: [West, East, North, South]", "Time view: [North, South, West, East]",
         "Original: [North, East, West, South]"),
        "Ranking practice checks passed",
    ),
}


def verify_role(folder, output, workbook, role):
    expected_files, first_failure, starter_demo, solution_demo, success = workbook
    assert {entry.name for entry in folder.iterdir()} == set(expected_files), folder
    sources = sorted(folder.glob("*.java"))
    subprocess.run([JAVA / "javac", "--release", "21", "-Xlint:all", "-Werror",
                    "-d", output, *sources], cwd=folder, check=True, timeout=20)
    result = subprocess.run([JAVA / "java", "-cp", output, "Main"], cwd=folder,
                            capture_output=True, text=True, timeout=20)
    assert result.returncode == 0 and tuple(result.stdout.splitlines()) == (
        starter_demo if role == "starter" else solution_demo
    ), result
    result = subprocess.run([JAVA / "java", "-cp", output, "ContractTests"], cwd=folder,
                            capture_output=True, text=True, timeout=20)
    if role == "starter":
        assert result.returncode != 0 and f"AssertionError: {first_failure}" in result.stderr, result
    else:
        assert result.returncode == 0 and result.stdout.strip() == success, result


if __name__ == "__main__":
    version = subprocess.check_output([JAVA / "javac", "-version"], text=True).strip()
    assert version == "javac 21" or version.startswith("javac 21."), version
    print(version, flush=True)
    for pack, workbook in WORKBOOKS.items():
        for role in ("starter", "solution"):
            with TemporaryDirectory(prefix="java-check-in-workbook-") as directory:
                verify_role(ROOT / pack / role, Path(directory), workbook, role)
        print(f"Verified {pack}: starter and reference", flush=True)

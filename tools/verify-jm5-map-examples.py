"""Exercise the original Java Level 2 map examples on Java 21."""

import os
from pathlib import Path
import subprocess
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"


def verify(folder_name, cases, entry="Main", probe=None):
    folder = ROOT / folder_name / "src/main/java"
    with TemporaryDirectory(prefix="java-jm5-original-") as tmp:
        output = Path(tmp)
        sources = sorted(folder.glob("*.java"))
        if probe:
            path = output / f"{entry}.java"
            path.write_text(probe)
            sources.append(path)
        subprocess.run(
            [JAVA / "javac", "--release", "21", "-Xlint:all", "-Werror", "-d", output, *sources],
            cwd=folder, timeout=20, check=True
        )
        for input_text, expected_tail in cases:
            run = subprocess.run([JAVA / "java", "-cp", output, entry], cwd=folder,
                                 input=input_text, text=True, capture_output=True, timeout=10)
            assert run.returncode == 0, run.stderr
            assert run.stdout.splitlines()[-len(expected_tail):] == expected_tail, run.stdout
    print(f"Verified {folder_name} with {len(cases)} cases", flush=True)


if __name__ == "__main__":
    version = subprocess.check_output([JAVA / "javac", "-version"], text=True).strip()
    assert version == "javac 21" or version.startswith("javac 21.")
    verify("JM5-Letter-Frequencies", [
        ("", ["No input received."]),
        ("   4?!\n", ["No letters found."]),
        ("B a A b!\n", ["a: 2", "b: 2", "Most frequent: a (2)"]),
        ("𐐀𐐨A\n", ["a: 1", "𐐨: 2", "Most frequent: 𐐨 (2)"]),
    ])
    verify("JM5-PROJECT-2-DealershipDatabase", [
        ("", ["No car found for VIN absent."]),
    ], "OriginalChecks", """
        public class OriginalChecks {
            public static void main(String[] args) {
                Dealership dealer = new Dealership();
                dealer.displayCar("absent");
                if (dealer.searchCar("absent") != null) {
                    throw new AssertionError("An unknown VIN added a car");
                }
            }
        }
    """)
    verify("JM5-Supplemental-Project-1-Lending-Library", [
        ("", ["No input received."]),
        ("banana\n", ["Input not valid! Please try again."]),
        ("1\n", ["No title received."]),
        ("2\n", ["No author received."]),
        ("1\nThe Giving Tree\n", ["We have the book The Giving Tree by Shel Silverstein"]),
        ("2\nDr. Seuss\n", ["Books by Dr. Seuss: ", "Green Eggs and Ham", "The Cat in the Hat"]),
    ])

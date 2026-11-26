"""Exercise the original Java Level 2 map examples on Java 21."""

import os
from pathlib import Path
import subprocess
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"


WORKBOOKS = {'JM5-Maps': (['Prices.java', 'Main.java', 'ContractTests.java', 'README.md'],
              'present key returns its value',
              ['Bread: 0', 'Missing: 0', 'Bread already present: true', 'Inventory: {bread=400}'],
              ['Bread: 375',
               'Missing: 0',
               'Bread already present: true',
               'Inventory: {banana=79, bread=400}'],
              'Map operations checks passed',
              ''),
 'JM5-Letter-Frequencies': (['LetterCounts.java', 'Main.java', 'ContractTests.java', 'README.md'],
                            'case folds and counts letters only',
                            ['No letters found.'],
                            ['a: 2', 'b: 2', 'Most frequent: a (2)'],
                            'Letter frequency checks passed',
                            'B a A b!\n'),
 'JM5-PROJECT-2-DealershipDatabase': (['src/main/java/vehicles/Vehicle.java',
                                       'src/main/java/vehicles/LandVehicle.java',
                                       'src/main/java/vehicles/Car.java',
                                       'src/main/java/vehicles/Dealership.java',
                                       'src/main/java/vehicles/Main.java',
                                       'src/test/java/vehicles/ContractTests.java',
                                       'sources.txt',
                                       'README.md'],
                                      'new keys are inserted',
                                      ['Duplicate accepted: false', 'Missing VIN: null'],
                                      ['Duplicate accepted: false',
                                       'Missing VIN: null',
                                       'DEMO-01: 2019 Camry, wheels 4, miles 13.5, doors 4',
                                       'DEMO-02: 2021 Civic, wheels 4, miles 30.5, doors 4'],
                                      'Dealership checks passed',
                                      ''),
 'JM5-Supplemental-Project-1-Lending-Library': (['Library.java',
                                                 'Main.java',
                                                 'ContractTests.java',
                                                 'README.md'],
                                                'author search returns all matches in title order',
                                                ['No matching titles.'],
                                                ['Green Eggs and Ham', 'The Cat in the Hat'],
                                                'Library checks passed',
                                                '2\nDr. Seuss\n'),
 'J2-05-jm5-maps-supplemental-2': (['Attendance.java', 'Main.java', 'ContractTests.java', 'README.md'],
                                   'first attendance is added',
                                   ['Repeat accepted: false', 'Lab A: 0', 'Lab B: 0', 'Missing room: 0'],
                                   ['Repeat accepted: false', 'Lab A: 2', 'Lab B: 1', 'Missing room: 0'],
                                   'Attendance checks passed',
                                   ''),
 'J2-06-jm5-maps-supplemental-3': (['Stock.java', 'Main.java', 'ContractTests.java', 'README.md'],
                                   'valid mixed request succeeds',
                                   ['First request: false',
                                    'After first: {folder=3, pen=5}',
                                    'Second request: false',
                                    'After second: {folder=3, pen=5}'],
                                   ['First request: true',
                                    'After first: {folder=2, pen=3}',
                                    'Second request: false',
                                    'After second: {folder=2, pen=3}'],
                                   'Stock checks passed',
                                   '')}


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


def verify_role(folder, output, workbook, role):
    expected_files, failure, starter_demo, solution_demo, success, input_text = workbook
    assert {path.relative_to(folder).as_posix() for path in folder.rglob("*") if path.is_file()} == set(expected_files)
    packaged = "sources.txt" in expected_files
    sources = sorted(name for name in expected_files if name.endswith(".java"))
    if packaged:
        assert sorted((folder / "sources.txt").read_text().splitlines()) == sources
    subprocess.run(
        [JAVA / "javac", "--release", "21", "-Xlint:all", "-Werror",
         "-d", output, *(["@sources.txt"] if packaged else sources)],
        cwd=folder, check=True, timeout=25
    )
    prefix = "vehicles." if packaged else ""

    def invoke(name, text=""):
        return subprocess.run([JAVA / "java", "-cp", output, prefix + name], cwd=folder,
                              input=text, text=True, capture_output=True, timeout=10)

    result = invoke("Main", input_text)
    assert result.returncode == 0 and result.stdout.splitlines() == (
        starter_demo if role == "starter" else solution_demo
    ), result
    result = invoke("ContractTests")
    if role == "starter":
        assert result.returncode != 0 and f"AssertionError: {failure}" in result.stderr, result
    else:
        assert result.returncode == 0 and result.stdout.strip() == success, result
        if "LetterCounts.java" in expected_files:
            for text, answer in (("", "No input received."), ("42?\n", "No letters found.")):
                result = invoke("Main", text)
                assert result.returncode == 0 and result.stdout.strip() == answer, result
        if "Library.java" in expected_files:
            for text, answer in (("banana\n", "Choose 1, 2, or 3."), ("1\n", "No title received."),
                                 ("2\n", "No author received."), ("1\nMissing\n", "Title not found.")):
                result = invoke("Main", text)
                assert result.returncode == 0 and result.stdout.strip() == answer, result


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
    for pack, workbook in WORKBOOKS.items():
        for role in ("starter", "solution"):
            with TemporaryDirectory(prefix="jm5-workbook-") as temporary:
                verify_role(ROOT / pack / role, Path(temporary), workbook, role)
        print(f"Verified {pack}: starter and reference", flush=True)

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
    "JM-Check-in-1": (
        ("Main.java", "Car.java", "ContractTests.java", "README.md"),
        "each construction counts once",
        ("A red sedan that has driven 20000 miles.", "A blue sports car that has driven 60000 miles.",
         "A yellow coupe that has driven 1500 miles.", "Cars created: 0"),
        ("A yellow coupe that has driven 1500 miles.", "A red sedan that has driven 20000 miles.",
         "A blue sports car that has driven 60000 miles.", "Cars created: 3"),
        "Car checks passed",
    ),
    "JM-Check-in-1-Additional-Project": (
        ("Main.java", "Card.java", "ContractTests.java", "README.md"),
        "suit sorts before rank",
        ("Unsorted: [2 of hearts, 13 of clubs, 1 of hearts, 1 of spades]",
         "Sorted: [2 of hearts, 13 of clubs, 1 of hearts, 1 of spades]"),
        ("Unsorted: [2 of hearts, 13 of clubs, 1 of hearts, 1 of spades]",
         "Sorted: [13 of clubs, 1 of hearts, 2 of hearts, 1 of spades]"),
        "Card checks passed",
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
    if "Car.java" in expected_files:
        result = subprocess.run([JAVA / "java", "-cp", output, "ContractTests", "extension"],
                                cwd=folder, capture_output=True, text=True, timeout=20)
        if role == "starter":
            assert result.returncode != 0 and "AssertionError: apply all valid distances" in result.stderr, result
        else:
            assert result.returncode == 0 and result.stdout.strip() == "Car extension checks passed", result



def verify_originals():
    probes = {
        "JM-Check-in-1": """
            Car car = new Car("coupe", "yellow", Integer.MAX_VALUE);
            if (car.sameMileage(null)) throw new AssertionError("Missing car matched");
            try {
                car.drive(1);
                throw new AssertionError("Overflow accepted");
            } catch (ArithmeticException expected) {
                if (car.getMileage() != Integer.MAX_VALUE) throw new AssertionError("Mileage changed");
            }
            reject(() -> new Car("invalid", "yellow", -1));
            if (Car.getNumCars() != 1) throw new AssertionError("Rejected car counted");
        """,
        "JM-Check-in-1-Additional-Project": """
            if (!new Card(1, 4).toString().equals("1 of spades")) throw new AssertionError("Suit changed");
            reject(() -> new Card(0, 1));
            reject(() -> new Card(1, 5));
        """,
    }
    for pack, code in probes.items():
        folder = ROOT / pack
        with TemporaryDirectory(prefix="check-in-original-") as directory:
            output = Path(directory)
            probe = output / "OriginalChecks.java"
            probe.write_text(
                "public class OriginalChecks { public static void main(String[] args) {"
                f"{code}"
                "} private static void reject(Runnable operation) {"
                "try { operation.run(); } catch (IllegalArgumentException expected) { return; }"
                'throw new AssertionError("Invalid input accepted"); } }\n'
            )
            subprocess.run([JAVA / "javac", "--release", "21", "-Xlint:all", "-Werror", "-d", output,
                            *sorted(folder.glob("*.java")), probe], cwd=folder, check=True, timeout=20)
            subprocess.run([JAVA / "java", "-cp", output, "OriginalChecks"],
                            cwd=folder, check=True, timeout=20)
        print(f"Verified original {pack}", flush=True)


if __name__ == "__main__":
    version = subprocess.check_output([JAVA / "javac", "-version"], text=True).strip()
    assert version == "javac 21" or version.startswith("javac 21."), version
    print(version, flush=True)
    for pack, workbook in WORKBOOKS.items():
        for role in ("starter", "solution"):
            with TemporaryDirectory(prefix="java-check-in-workbook-") as directory:
                verify_role(ROOT / pack / role, Path(directory), workbook, role)
        print(f"Verified {pack}: starter and reference", flush=True)
    verify_originals()

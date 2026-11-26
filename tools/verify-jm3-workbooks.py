"""Verify the JM3 static-state and ordering projects on Java 21."""

import os
import subprocess
from pathlib import Path
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"

WORKBOOKS = {
    "JM3-Quiz-Class": (
        ("Main.java", "Quiz.java", "ContractTests.java", "README.md"),
        "no-argument constructor counts once",
        ("Review: 92 (A), first question: (pending)", "Practice: 80 (B), first question: (pending)", "Quizzes created: 0"),
        ("Review: 92 (A), first question: Which values belong to an instance?", "Practice: 80 (B), first question: (none)", "Quizzes created: 2"),
        "Quiz checks passed",
    ),
    "JM3-House-Class": (
        ("Main.java", "House.java", "ContractTests.java", "README.md"),
        "price is the primary key",
        ("Oak Lane | $500000 | 1800 sq ft", "Cedar Lane | $500000 | 1200 sq ft", "Elm Lane | $250000 | 2000 sq ft", "Houses created: 0"),
        ("Elm Lane | $250000 | 2000 sq ft", "Cedar Lane | $500000 | 1200 sq ft", "Oak Lane | $500000 | 1800 sq ft", "Houses created: 3"),
        "House checks passed",
    ),
    "JM3-Supplemental-Project-1-Basketball-Statistics": (
        ("Main.java", "Player.java", "ContractTests.java", "README.md"),
        "combined points and rebounds",
        ("Riley | total 0 | fouls 6 | eligible", "Casey | total 0 | fouls 5 | eligible", "Jordan | total 0 | fouls 2 | eligible"),
        ("Casey | total 16 | fouls 5 | eligible", "Riley | total 18 | fouls 6 | disqualified", "Jordan | total 20 | fouls 2 | eligible"),
        "Basketball checks passed",
    ),
    "J2-01-jm3-static-variables-and-methods-supplemental-3": (
        ("Main.java", "ContractTests.java", "README.md"),
        "even and odd scoring rules",
        ("0",),
        ("41",),
        "Static scoring checks passed",
    ),
}

ORIGINAL_PROBES = {
    "JM3-Quiz-Class": """
        Quiz blank = new Quiz();
        Quiz noQuestions = new Quiz("Practice", 82);
        Quiz emptyQuestions = new Quiz("Practice", 82, new String[0]);
        for (Quiz quiz : new Quiz[]{blank, noQuestions, emptyQuestions}) {
            check(quiz.toString().endsWith("(none)"), "empty quiz prints no question");
        }
        String[] questions = {"first question"};
        Quiz oneQuestion = new Quiz("Practice", 82, questions);
        questions[0] = "changed outside the quiz";
        check(oneQuestion.toString().endsWith("first question"), "use the actual question count and copy its array");
        check(oneQuestion.getLetterGrade().equals("B"), "retain the score on the instance");
        check(Quiz.getNumQuizzes() == 4, "each public constructor adds one quiz");
    """,
    "JM3-Supplemental-Project-1-Basketball-Statistics": """
        Player lower = new Player("Lower", 1, 1, 5);
        Player higher = new Player("Higher", Integer.MAX_VALUE, Integer.MAX_VALUE, 6);
        check(higher.compareTo(lower) > 0 && lower.compareTo(higher) < 0,
              "compare large totals without overflowing an int");
        check(lower.compareTo(new Player("Tie", 2, 0, 0)) == 0,
              "equal combined totals tie");
        check(higher.isDisqualified() && !lower.isDisqualified(),
              "foul threshold remains six");
    """,
}


def verify_original(folder, code):
    with TemporaryDirectory(prefix="jm3-original-") as directory:
        output = Path(directory)
        probe = output / "LegacyChecks.java"
        probe.write_text(
            "public class LegacyChecks { public static void main(String[] args) {"
            f"{code}"
            '} static void check(boolean result, String description) {'
            'if (!result) throw new AssertionError(description); } }\n'
        )
        sources = sorted(folder.glob("*.java"))
        subprocess.run([JAVA / "javac", "--release", "21", "-Xlint:all", "-Werror",
                        "-d", output, *sources, probe], cwd=folder, check=True, timeout=20)
        subprocess.run([JAVA / "java", "-cp", output, "LegacyChecks"], cwd=folder, check=True, timeout=20)
    print(f"Verified original {folder.name}", flush=True)


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
            with TemporaryDirectory(prefix="jm3-workbook-") as directory:
                verify_role(ROOT / pack / role, Path(directory), workbook, role)
        print(f"Verified {pack}: starter and reference", flush=True)
    for pack, probe in ORIGINAL_PROBES.items():
        verify_original(ROOT / pack, probe)

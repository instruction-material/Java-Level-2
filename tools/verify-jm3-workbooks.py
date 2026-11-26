"""Verify the JM3 original static-state and ordering examples on Java 21."""

import os
import subprocess
from pathlib import Path
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"

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


if __name__ == "__main__":
    version = subprocess.check_output([JAVA / "javac", "-version"], text=True).strip()
    assert version == "javac 21" or version.startswith("javac 21."), version
    print(version, flush=True)
    for pack, probe in ORIGINAL_PROBES.items():
        verify_original(ROOT / pack, probe)

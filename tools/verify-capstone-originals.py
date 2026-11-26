"""Verify the original console examples with temporary inputs and score files."""

import os
from pathlib import Path
import subprocess
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"


def compile_project(folder, output):
    files = sorted((folder / "src/main/java").glob("*.java"))
    subprocess.run(
        [JAVA / "javac", "--release", "21", "-Xlint:all", "-Werror", "-d", output, *files],
        cwd=folder, check=True, timeout=25,
    )


def play(output, working, commands):
    result = subprocess.run(
        [JAVA / "java", "-cp", output, "Main"],
        cwd=working, input=commands, text=True, capture_output=True, check=True, timeout=4,
    )
    assert result.stderr == "", result
    return result.stdout


with TemporaryDirectory(prefix="java2-capstone-originals-") as workspace:
    work = Path(workspace)
    quo = work / "quiz-classes"
    ttt = work / "board-classes"
    ttt.mkdir()
    quo.mkdir()
    compile_project(ROOT / "JM10-Tic-Tac-Toe-Console", ttt)
    compile_project(ROOT / "JM-Master-Project-Example-Quiz-Game", quo)

    assert "Input ended." in play(ttt, work, "")
    assert "Game ended." in play(ttt, work, "q\n")
    won = play(ttt, work, "wrong\n1 1\n1 1\n2 1\n1 2\n2 2\n1 3\n")
    assert won.count("Choose an open cell") == 2 and "Player X wins!" in won
    tied = play(ttt, work, "1 1\n1 2\n1 3\n2 1\n2 3\n2 2\n3 2\n3 3\n3 1\n")
    assert "The game is a tie!" in tied

    fixture = work / "Questions.txt"
    scores = work / "highscores.txt"
    assert "Cannot read Questions.txt." in play(quo, work, "")
    correct = "General\nPick the second option.\nA,B\nB\n"
    fixture.write_text(correct, encoding="utf-8")
    assert "Input ended." in play(quo, work, "") and not scores.exists()
    assert "Finish all questions to save a score." in play(quo, work, "General\n") and not scores.exists()
    recovered = play(quo, work, "unknown\nGeneral\nbad\n0\n3\n2\n")
    assert "Choose a listed category." in recovered and "Use a whole number." in recovered
    assert "Your final score: 1/1" in recovered
    assert scores.read_text(encoding="utf-8") == "Category: General | Score: 1\n"

    for broken in ("Incomplete", "General\nDuplicate choices\nA,A\nA\n", "History\nEmpty option\nA,\nA\n"):
        fixture.write_text(correct + broken, encoding="utf-8")
        output = play(quo, work, "General\n2\n")
        assert "Invalid question starting on line 5." in output and "Your final score" not in output
        assert scores.read_text(encoding="utf-8") == "Category: General | Score: 1\n"

    supplied = ROOT / "JM-Master-Project-Example-Quiz-Game" / "Questions.txt"
    fixture.write_bytes(supplied.read_bytes())
    actual = play(quo, work, "Science\n1\n1\n1\n1\n1\n")
    assert "Choose a category: [Science, History, Geography, Pop Culture]" in actual
    assert "Your final score:" in actual and "/5" in actual

print("Verified board win, tie, quit and EOF, plus quiz recovery, rejected files and exact saved score.")

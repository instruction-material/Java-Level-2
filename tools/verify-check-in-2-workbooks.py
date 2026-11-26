"""Exercise the Java Level 2 clock check-in and optional workbooks."""

import os
from pathlib import Path
import subprocess
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"
SYMBOLS = "I II III IV V VI VII VIII IX X XI XII".split()
ROLLOVERS = ["12-hour rollover: 1", "24-hour rollover: 0"]
RETRY = ["Hour outside clock range.", "Still at: 8"]

WORKBOOKS = {
    "JM-Check-in-2-Updated": (
        ("HourClock.java", "RomanClock.java"), "Roman eight", "Clock and map checks passed",
        ROLLOVERS + ["Roman Time: VIII"]
        + [f"{symbol} maps to: {value}" for value, symbol in enumerate(SYMBOLS, 1)]
        + ["L maps to: 50"] + RETRY + ["Roman Time: IX"],
        ROLLOVERS + ["Time: 8:00"] + RETRY + ["Time: 9:00"],
    ),
    "JM-Check-in-2-Additional-Project": (
        ("HourClock.java", "MinuteClock.java"), "normal minute advances once",
        "Minute rollover checks passed",
        ["Time: 12:58", "Time: 12:59", "Time: 1:00", "Time: 0:00"],
        ["Time: 12:00"] * 3 + ["Time: 23:00"],
    ),
    "JM-Check-in-2-Clock-Playback": (
        ("HourClock.java", "ClockPlayback.java"), "four ordered clock readings",
        "Clock playback checks passed", ["Hours: [11, 12, 1, 2]"], ["Hours: [11, 11, 11, 11]"],
    ),
    "J2-09-check-in-2-supplemental-2": (
        ("SensorReadings.java",), "accept valid reading", "Sensor recovery checks passed",
        ["Rejected: true", "After rejection: 7", "After retry: -2"],
        ["Rejected: true", "After rejection: null", "After retry: null"],
    ),
    "J2-10-check-in-2-supplemental-3": (
        ("StationHours.java",), "positive midnight rollover and order", "Station report checks passed",
        ["At 23: {Harbor=3, Ridge=20}", "At 1: {Harbor=5, Ridge=22}"], ["At 23: {}", "At 1: {}"],
    ),
}


def compile_demo(folder, output, names, expected):
    subprocess.run(
        [JAVA / "javac", "--release", "21", "-Xlint:all", "-Werror", "-d", output, *names],
        cwd=folder, check=True, timeout=25,
    )
    result = run_class(output, "Main")
    assert result.returncode == 0 and result.stdout.splitlines() == expected, result


def run_class(output, name):
    return subprocess.run(
        [JAVA / "java", "-cp", output, name],
        text=True, capture_output=True, timeout=10,
    )


def verify_role(folder, output, workbook, role):
    models, failure, success, solution, starter = workbook
    files = ("Main.java", *models, "ContractTests.java")
    assert {path.relative_to(folder).as_posix() for path in folder.rglob("*") if path.is_file()} == {
        *files, "README.md",
    }
    compile_demo(folder, output, files, solution if role == "solution" else starter)
    result = run_class(output, "ContractTests")
    if role == "starter":
        assert result.returncode != 0 and f"AssertionError: {failure}" in result.stderr, result
    else:
        assert result.returncode == 0 and result.stdout.strip() == success, result


if __name__ == "__main__":
    version = subprocess.check_output([JAVA / "javac", "-version"], text=True).strip()
    assert version == "javac 21" or version.startswith("javac 21."), version
    for pack, workbook in WORKBOOKS.items():
        for role in ("starter", "solution"):
            with TemporaryDirectory(prefix="check-in-2-role-") as output:
                verify_role(ROOT / pack / role, output, workbook, role)
        print(f"Verified starter and solution for {pack}", flush=True)
    for original, source, expected in (
        ("JM-Check-in-2-Starter-Updated", "JM-Check-in-2-Updated/starter", WORKBOOKS["JM-Check-in-2-Updated"][-1]),
        ("JM-Check-in-2-Updated", "JM-Check-in-2-Updated/solution", WORKBOOKS["JM-Check-in-2-Updated"][-2]),
        ("JM-Check-in-2-Additional-Project", "JM-Check-in-2-Additional-Project/solution",
         WORKBOOKS["JM-Check-in-2-Additional-Project"][-2]),
    ):
        directory = ROOT / original
        sources = sorted(file.name for file in directory.glob("*.java"))
        assert len(sources) == 3, sources
        for name in sources:
            assert (directory / name).read_bytes() == (ROOT / source / name).read_bytes(), (original, name)
        with TemporaryDirectory(prefix="check-in-2-original-") as output:
            compile_demo(directory, output, sources, expected)
    print("Verified all three original paths terminate with the expected outputs", flush=True)

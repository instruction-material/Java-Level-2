"""Compile and exercise the exception and concurrency workbooks with Java 21."""

import os
from pathlib import Path
import subprocess
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"


WORKBOOKS = {
    "JM6-Try-Catch-Practice": (
        "Recovery", "ordinary division", "Exception recovery checks passed", "",
        ["Quotient: 4", "Cannot divide by zero.", "Quotient: 6", "Value: 9",
         "Index outside array.", "Index outside array.", "Value: 15"],
        ["Division pending."] * 3 + ["Lookup pending."] * 4,
    ),
    "JM6-Bouncing-Zeros": (
        "SimpleAnimation", "seven-frame bounce", "Animation checks passed", "",
        ["0...", ".0..", "..0.", "...0", "..0.", ".0..", "0...", "Animation complete."],
        ["0..."] * 7 + ["Animation complete."],
    ),
    "JM6-Supplemental-Project-1-Barnyard-Orchestra": (
        "AnimalPlayer", "each worker records two sounds", "Orchestra checks passed",
        "cow 0\npig 0\nfrog 0\n",
        ["cow: [moo, moo]", "pig: [oink, oink]", "frog: [ribbit, ribbit]"],
        ["cow: [moo]", "pig: [oink]", "frog: [ribbit]"],
    ),
    "J2-07-jm6-threading-and-error-handling-supplemental-2": (
        "TemperatureBatch", "worker uses its own copy and accepts negative readings",
        "Weather batch checks passed", "",
        ["Hill: min -3, max 5", "Lake: min 7, max 7"],
        ["Hill: Report pending.", "Lake: Report pending."],
    ),
    "J2-08-jm6-threading-and-error-handling-supplemental-3": (
        "GateWorker", "released signal succeeds", "Worker gate checks passed", "",
        ["First job: Released", "Second job: Cancelled"],
        ["First job: Pending", "Second job: Pending"],
    ),
}


def compile_to(folder, output, names):
    subprocess.run(
        [JAVA / "javac", "--release", "21", "-Xlint:all", "-Werror", "-d", output, *names],
        cwd=folder, check=True, timeout=25,
    )


def run_class(output, name, input_text=""):
    return subprocess.run(
        [JAVA / "java", "-cp", output, name],
        input=input_text, text=True, capture_output=True, timeout=10,
    )


def verify_demo(output, input_text, expected):
    result = run_class(output, "Main", input_text)
    assert result.returncode == 0 and result.stdout.splitlines() == expected, result


def verify_role(folder, output, workbook, role):
    model, failure, success, input_text, solution, starter = workbook
    files = {"Main.java", f"{model}.java", "ContractTests.java", "README.md"}
    assert {path.relative_to(folder).as_posix() for path in folder.rglob("*") if path.is_file()} == files
    compile_to(folder, output, sorted(name for name in files if name.endswith(".java")))
    verify_demo(output, input_text, solution if role == "solution" else starter)
    result = run_class(output, "ContractTests")
    if role == "starter":
        assert result.returncode != 0 and f"AssertionError: {failure}" in result.stderr, result
    else:
        assert result.returncode == 0 and result.stdout.strip() == success, result
    if model == "AnimalPlayer":
        for inputs, expected in (
            ("cow 0\n", "Enter three animal and delay lines."),
            ("cow 0\nshark 0\n", "Use a known animal and one delay."),
            ("cow 0\npig bad\n", "Use a whole delay from 0 to 1000."),
            ("cow 0\npig 1001\n", "Use a whole delay from 0 to 1000."),
        ):
            verify_demo(output, inputs, [expected])


if __name__ == "__main__":
    version = subprocess.check_output([JAVA / "javac", "-version"], text=True).strip()
    assert version == "javac 21" or version.startswith("javac 21."), version
    for pack, workbook in WORKBOOKS.items():
        for role in ("starter", "solution"):
            with TemporaryDirectory(prefix="jm6-contract-") as output:
                verify_role(ROOT / pack / role, output, workbook, role)
        legacy = ROOT / pack / "src/main/java"
        if legacy.exists():
            model, _, _, input_text, expected, _ = workbook
            names = ("Main.java", f"{model}.java")
            for name in names:
                assert (legacy / name).read_bytes() == (ROOT / pack / "solution" / name).read_bytes(), name
            with TemporaryDirectory(prefix="jm6-original-") as output:
                compile_to(legacy, output, names)
                verify_demo(output, input_text, expected)
        print(f"Verified starter, solution and applicable original for {pack}", flush=True)

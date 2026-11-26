"""Run the Java 21 behavior checks for the three JM2 source pairs."""

import os
import subprocess
from pathlib import Path
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"

BANNER = "Enter flavor, iced (Y or N), and topping (blank for none), one per line."
POPULAR = "brown sugar milk tea\nY\npearls\n"
WORKBOOKS = (
    (
        "JM2-Person-Class",
        "Person",
        "the text grade becomes a numeric grade",
        ("Name: Abby, Grade: 2, Age: 9", "Name: Jack, Grade: 0, Age: 12",
         "Name: Tiffany, Grade: 0, Age: 10", "Name: , Grade: 0, Age: 0"),
        ("Name: Abby, Grade: 2, Age: 9", "Name: Jack, Grade: 6, Age: 12",
         "Name: Tiffany, Grade: 0, Age: 10", "Name: Unknown, Grade: 0, Age: 0"),
    ),
    (
        "JM2-Book-Class",
        "Book",
        "same author can write different titles",
        ("Title: Redwall, Author: Brian Jacques, Page Number: 0", "Same author: false", "Same title and author: false"),
        ("Title: Redwall, Author: Brian Jacques, Page Number: 20", "Same author: true", "Same title and author: true"),
    ),
    (
        "JM2-Supplemental-Project-2-Boba",
        "Boba",
        "an omitted topping uses the empty string",
        (BANNER, "Flavor: brown sugar milk tea, Iced: true, Topping: undecided"),
        (BANNER, "Flavor: brown sugar milk tea, Iced: true, Topping: pearls", "Matches the most popular order."),
    ),
)


def run(command, folder, stdin=None):
    return subprocess.run(command, cwd=folder, capture_output=True, text=True, input=stdin, timeout=20)


def run_class(folder, out, entry, stdin=None):
    return run([JAVA / "java", "-cp", out, entry], folder, stdin)


def compile_java(folder, out, extra_sources=()):
    sources = [*sorted(folder.glob("*.java")), *extra_sources]
    assert sources, folder
    result = run([JAVA / "javac", "--release", "21", "-Xlint:all", "-Werror", "-d", out, *sources], folder)
    assert result.returncode == 0, result.stderr


def check_output(program, lines):
    assert program.returncode == 0 and tuple(program.stdout.splitlines()) == lines, program


def verify_role(folder, out, model, role, failure, starter_output, solution_output):
    assert {item.name for item in folder.iterdir()} == {
        "Main.java", f"{model}.java", "ContractTests.java", "README.md"
    }
    compile_java(folder, out)
    demo = run_class(folder, out, "Main", POPULAR if model == "Boba" else None)
    check_output(demo, starter_output if role == "starter" else solution_output)
    checks = run_class(folder, out, "ContractTests")
    if role == "starter":
        assert checks.returncode != 0 and f"AssertionError: {failure}" in checks.stderr, checks
        return
    check_output(checks, (f"{model} class checks passed",))
    if model == "Boba":
        check_output(run_class(folder, out, "Main", "taro milk tea\nn\n\n"),
                     (BANNER, "Flavor: taro milk tea, Iced: false, Topping: none"))
        check_output(run_class(folder, out, "Main", "taro milk tea\n"), (BANNER, "Order incomplete."))
        check_output(run_class(folder, out, "Main", "taro milk tea\nmaybe\n\n"),
                     (BANNER, "Use a flavor and Y or N for iced."))


ORIGINAL_PROBES = {
    "JM2-Person-Class": """
        if (!new Person("Jack", 12, "6").toString().contains("Grade: 6")) {
            throw new AssertionError("Text grade was lost");
        }
        if (!new Person().toString().startsWith("Name: Unknown")) {
            throw new AssertionError("No-argument defaults were lost");
        }
    """,
    "JM2-Book-Class": """
        Book copy = new Book("Title", "Author");
        if (copy.hasSameAuthor(null) || copy.isSameBook(null)) {
            throw new AssertionError("Missing comparison book matched");
        }
        if (!copy.isSameBook(new Book("Title", "Author", 5))) {
            throw new AssertionError("Bookmark was used to identify a book");
        }
    """,
    "JM2-Supplemental-Project-2-Boba": """
        Boba plain = new Boba("taro milk tea", false);
        if (!plain.isSameOrder(new Boba("taro milk tea", false)) ||
            plain.isSameOrder(new Boba("taro milk tea", false, "pearls")) ||
            plain.isSameOrder(null)) {
            throw new AssertionError("Missing toppings were compared incorrectly");
        }
    """,
}


def verify_original(folder, out):
    probe = out / "LegacyChecks.java"
    probe.write_text(
        "public class LegacyChecks { public static void main(String[] args) {"
        f"{ORIGINAL_PROBES[folder.name]}"
        "System.out.println(\"Original example checks passed\"); } }\n"
    )
    compile_java(folder, out, [probe])
    check_output(run_class(folder, out, "LegacyChecks"), ("Original example checks passed",))


if __name__ == "__main__":
    version = subprocess.check_output([JAVA / "javac", "-version"], text=True).strip()
    assert version == "javac 21" or version.startswith("javac 21."), version
    print(version, flush=True)
    for pack, model, failure, starter_output, solution_output in WORKBOOKS:
        for role in ("starter", "solution"):
            with TemporaryDirectory(prefix="jm2-workbooks-") as temporary:
                verify_role(ROOT / pack / role, Path(temporary), model, role, failure, starter_output, solution_output)
        with TemporaryDirectory(prefix="jm2-original-") as temporary:
            verify_original(ROOT / pack, Path(temporary))
        print(f"Verified {pack}: starter, reference, and original", flush=True)

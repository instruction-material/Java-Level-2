"""Verify the Java Level 2 JM4 starter/reference behavior and retained examples."""

import os
from pathlib import Path
import subprocess
from tempfile import TemporaryDirectory

ROOT = Path(__file__).resolve().parents[1]
JAVA = Path(os.environ["JAVA_HOME"]) / "bin"

WORKBOOKS = {'JM4-Rectangle-and-Square-Class': (['Rectangle.java',
                                     'Square.java',
                                     'Main.java',
                                     'ContractTests.java',
                                     'README.md'],
                                    'parent reference dispatches to Square',
                                    ['Rectangle: area 15, perimeter 16',
                                     'Rectangle: area 16, perimeter 16'],
                                    ['Rectangle: area 15, perimeter 16',
                                     'Square: area 16, perimeter 16'],
                                    'Rectangle checks passed'),
 'JM4-Book-and-Picture-Book-Class': (['Book.java',
                                      'PictureBook.java',
                                      'Main.java',
                                      'ContractTests.java',
                                      'README.md'],
                                     'matching illustrated edition uses all three fields',
                                     ['The Lantern by A. Reed, page 5',
                                      'Same work: true',
                                      'Same illustrated edition: false'],
                                     ['The Lantern by A. Reed, page 5, illustrated by K. Lin',
                                      'Same work: true',
                                      'Same illustrated edition: false'],
                                     'PictureBook checks passed'),
 'JM4-Project-3-Vehicle-Inheritance': (['src/main/java/vehicles/Vehicle.java',
                                        'src/main/java/vehicles/LandVehicle.java',
                                        'src/main/java/vehicles/Car.java',
                                        'src/main/java/vehicles/Motorcycle.java',
                                        'src/main/java/vehicles/Main.java',
                                        'src/test/java/vehicles/ContractTests.java',
                                        'sources.txt',
                                        'README.md'],
                                       'car details through vehicle reference',
                                       ['2019 Camry, wheels 4, miles 13.5',
                                        '2020 Sportster, wheels 2, miles 4.0'],
                                       ['2019 Camry, wheels 4, miles 13.5, doors 4',
                                        '2020 Sportster, wheels 2, miles 4.0, sidecar false'],
                                       'Vehicle checks passed'),
 'JM4-Supplemental-Project-1-Pet-Dog-and-Cat-Class': (['Pet.java',
                                                       'Dog.java',
                                                       'Cat.java',
                                                       'Main.java',
                                                       'ContractTests.java',
                                                       'README.md'],
                                                      'cat sound dispatches through Pet',
                                                      ['Cookie says Bark',
                                                       'Cheeto says (pending)',
                                                       'Service dog: true',
                                                       'Jump distance: 5'],
                                                      ['Cookie says Bark',
                                                       'Cheeto says Meow',
                                                       'Service dog: true',
                                                       'Jump distance: 5'],
                                                      'Pet checks passed'),
 'JM4-Lesson-and-DanceLesson-Class': (['Lesson.java',
                                       'DanceLesson.java',
                                       'Main.java',
                                       'ContractTests.java',
                                       'README.md'],
                                      'parent view displays dance details',
                                      ['Art, 30 minutes',
                                       'Dance, 45 minutes',
                                       'Dance, 60 minutes',
                                       'Ballet is easier: false'],
                                      ['Art, 30 minutes',
                                       'Dance, 45 minutes, jazz (difficulty 3)',
                                       'Dance, 60 minutes, ballet (difficulty 2)',
                                       'Ballet is easier: true'],
                                      'Dance lesson checks passed'),
 'J2-04-jm4-subclasses-and-inheritance-supplemental-3': (['Address.java',
                                                          'Delivery.java',
                                                          'PriorityDelivery.java',
                                                          'Main.java',
                                                          'ContractTests.java',
                                                          'README.md'],
                                                         'priority adds the surcharge through the '
                                                         'parent reference',
                                                         ['Market Lane: 500 cents',
                                                          'Market Lane: 500 cents'],
                                                         ['Market Lane: 500 cents',
                                                          'Market Lane: 800 cents'],
                                                         'Delivery checks passed')}

def invoke(folder, command):
    return subprocess.run(command, cwd=folder, text=True, capture_output=True, timeout=25)


def compile_sources(folder, output, inputs):
    result = invoke(folder, [JAVA / "javac", "--release", "21", "-Xlint:all", "-Werror",
                             "-d", output, *inputs])
    assert result.returncode == 0, result.stderr


def verify_role(folder, output, workbook, role):
    expected_files, first_failure, starter_demo, solution_demo, success = workbook
    assert {entry.relative_to(folder).as_posix() for entry in folder.rglob("*") if entry.is_file()} == set(expected_files)
    packaged = "sources.txt" in expected_files
    sources = sorted(name for name in expected_files if name.endswith(".java"))
    if packaged:
        assert sorted((folder / "sources.txt").read_text().splitlines()) == sources
    compile_sources(folder, output, ["@sources.txt"] if packaged else sources)
    prefix = "vehicles." if packaged else ""
    result = invoke(folder, [JAVA / "java", "-cp", output, prefix + "Main"])
    assert result.returncode == 0 and result.stdout.splitlines() == (
        starter_demo if role == "starter" else solution_demo
    ), result
    result = invoke(folder, [JAVA / "java", "-cp", output, prefix + "ContractTests"])
    if role == "starter":
        assert result.returncode != 0 and f"AssertionError: {first_failure}" in result.stderr, result
    else:
        assert result.returncode == 0 and result.stdout.strip() == success, result


ORIGINALS = {
    "JM4-Rectangle-and-Square-Class": '''
        Rectangle rectangle = new Square(4);
        if (rectangle.getArea() != 16 || rectangle.getPerimeter() != 16) throw new AssertionError("Square geometry");
        try { new Square(-1); } catch (IllegalArgumentException expected) { return; }
        throw new AssertionError("Invalid square accepted");
    ''',
    "JM4-Book-and-Picture-Book-Class": '''
        PictureBook one = new PictureBook("Story", "A", 1, "X");
        PictureBook two = new PictureBook("Story", "A", 9, "Y");
        Book parent = one;
        if (!parent.isSameBook(two) || !one.isSameBook(two) ||
            one.isSameIllustratedEdition(two) || one.isSameIllustratedEdition(null)) {
            throw new AssertionError("Work and illustrated edition comparisons disagree");
        }
    ''',
    "JM4-Project-3-Vehicle-Inheritance/src/main/java": '''
        LandVehicle vehicle = new LandVehicle("Demo", "Car", 2000, "red", 4, "electric", 17);
        vehicle.increaseMiles(Double.POSITIVE_INFINITY);
        if (vehicle.getMiles() != 17) throw new AssertionError("Odometer accepted infinity");
        vehicle.increaseMiles(2.5);
        if (vehicle.getMiles() != 19.5) throw new AssertionError("Ordinary mileage was lost");
    ''',
    "JM4-Supplemental-Project-1-Pet-Dog-and-Cat-Class": '''
        if (!new Pet("Foo", 2, 3).toString().equals("Name: Foo, Weight: 2, Age: 3")) {
            throw new AssertionError("Unexpected pet display");
        }
    ''',
    "JM4-Lesson-and-DanceLesson-Class": '''
        DanceLesson lesson = new DanceLesson(30, 5, 2, "jazz");
        if (lesson.hasSameDifficulty(null) || lesson.easierThan(null)) {
            throw new AssertionError("Missing comparison accepted");
        }
    ''',
}


if __name__ == "__main__":
    version = subprocess.check_output([JAVA / "javac", "-version"], text=True).strip()
    assert version == "javac 21" or version.startswith("javac 21."), version
    print(version, flush=True)
    for pack, workbook in WORKBOOKS.items():
        for role in ("starter", "solution"):
            with TemporaryDirectory(prefix="jm4-workbook-") as path:
                verify_role(ROOT / pack / role, Path(path), workbook, role)
        print(f"Verified {pack}: starter and reference", flush=True)
    for pack, code in ORIGINALS.items():
        folder = ROOT / pack
        with TemporaryDirectory(prefix="jm4-original-") as path:
            output = Path(path)
            probe = output / "OriginalChecks.java"
            probe.write_text(
                "public class OriginalChecks { public static void main(String[] args) {"
                + code + "} }\n"
            )
            compile_sources(folder, output, [*sorted(folder.glob("*.java")), probe])
            result = invoke(folder, [JAVA / "java", "-cp", output, "OriginalChecks"])
            assert result.returncode == 0, result.stderr
        print(f"Verified original {pack}", flush=True)

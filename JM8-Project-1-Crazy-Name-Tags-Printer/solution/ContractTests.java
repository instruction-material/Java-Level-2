import java.nio.file.Files;
import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        try (var folder = new WorkbookFolder()) {
            var file = folder.file("tags.txt");
            NameTags.write(file, " Zoë ");
            check(Files.readAllLines(file).equals(List.of("ZOË", "Zë", "ëoZ")), "three label transformations");
            NameTags.write(file, "A🌼B");
            check(Files.readAllLines(file).equals(List.of("A🌼B", "AB", "B🌼A")), "retain supplementary code point");
            try {
                NameTags.write(file, " ");
                throw new AssertionError("blank label accepted");
            } catch (IllegalArgumentException expected) {
                check(Files.readAllLines(file).get(1).equals("AB"), "reject before writing");
            }
        }
        System.out.println("Name tag checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

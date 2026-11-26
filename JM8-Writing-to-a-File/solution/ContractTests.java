import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        try (var folder = new WorkbookFolder()) {
            var path = folder.file("rows.txt");
            FileLines.replace(path, List.of("Café"));
            FileLines.append(path, "next");
            check(Files.readAllLines(path, StandardCharsets.UTF_8).equals(List.of("Café", "next")), "retain existing line when appending");
            FileLines.replace(path, List.of("fresh"));
            check(Files.readAllLines(path).equals(List.of("fresh")), "replace drops old lines");
            var newPath = folder.file("new.txt");
            FileLines.append(newPath, "first");
            check(Files.readAllLines(newPath).equals(List.of("first")), "append creates missing file");
        }
        System.out.println("File writing checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

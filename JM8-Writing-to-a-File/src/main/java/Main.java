import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

class Main {
    public static void main(String[] args) throws Exception {
        try (var folder = new WorkbookFolder()) {
            var file = folder.file("rows.txt");
            FileLines.replace(file, List.of("A", "Café"));
            FileLines.append(file, "End");
            System.out.println("After append: " + Files.readAllLines(file, StandardCharsets.UTF_8));
            FileLines.replace(file, List.of("New"));
            System.out.println("After replace: " + Files.readAllLines(file, StandardCharsets.UTF_8));
        }
    }
}

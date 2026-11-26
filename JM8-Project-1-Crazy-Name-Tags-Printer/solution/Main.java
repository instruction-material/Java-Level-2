import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

class Main {
    public static void main(String[] args) throws Exception {
        try (var folder = new WorkbookFolder()) {
            var file = folder.file("tags.txt");
            String label = args.length == 0 ? "Zoë" : args[0];
            NameTags.write(file, label);
            System.out.println("Tags: " + Files.readAllLines(file, StandardCharsets.UTF_8));
        } catch (IllegalArgumentException invalidLabel) {
            System.out.println(invalidLabel.getMessage());
        }
    }
}

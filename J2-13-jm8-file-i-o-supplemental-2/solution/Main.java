import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

class Main {
    public static void main(String[] args) throws Exception {
        try (var folder = new WorkbookFolder()) {
            var file = folder.file("journal.txt");
            Files.writeString(file, "Cedar\nFern\n\n cedar \n", StandardCharsets.UTF_8);
            System.out.println("Counts: " + JournalCounts.read(file));
        }
    }
}

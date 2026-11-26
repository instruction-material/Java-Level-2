import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

class Main {
    public static void main(String[] args) throws IOException {
        try (var folder = new WorkbookFolder()) {
            var file = folder.file("numbers.txt");
            Files.copy(Path.of("numbers.txt"), file);
            System.out.println("Total: " + NumberFile.sum(file));
        } catch (NoSuchFileException missing) {
            System.out.println("Number file is missing.");
        } catch (IllegalArgumentException invalid) {
            System.out.println(invalid.getMessage());
        }
    }
}

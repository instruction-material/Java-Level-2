import java.nio.file.Files;
import java.nio.file.Path;

class Main {
    public static void main(String[] args) throws Exception {
        try (var folder = new WorkbookFolder()) {
            var copy = folder.file("input.txt");
            Files.copy(Path.of("input.txt"), copy);
            System.out.println("Pairs: " + PairFile.read(copy));
        }
    }
}

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

final class FileLines {
    static void replace(Path file, List<String> rows) throws IOException {
        Files.write(file, rows, StandardCharsets.UTF_8);
    }

    static void append(Path file, String row) throws IOException {
        // TODO: Append a UTF-8 row without replacing earlier rows.
    }
}

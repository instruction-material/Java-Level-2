import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

final class WorkbookFolder implements AutoCloseable {
    private final Path root = Files.createTempDirectory("java-file-workbook-");

    WorkbookFolder() throws IOException {
    }

    Path file(String name) {
        return root.resolve(name);
    }

    @Override
    public void close() throws IOException {
        try (var files = Files.list(root)) {
            for (Path file : files.toList()) Files.deleteIfExists(file);
        }
        Files.deleteIfExists(root);
    }
}

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

final class NumberFile {
    static long sum(Path file) throws IOException {
        long total = 0;
        try (var reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            int number = 0;
            for (String row = reader.readLine(); row != null; row = reader.readLine()) {
                number++;
                try {
                    int value = Integer.parseInt(row.strip());
                    // TODO: Include the parsed number in the total.
                } catch (NumberFormatException malformed) {
                    throw new IllegalArgumentException("Invalid number on line " + number + ".", malformed);
                }
            }
        }
        return total;
    }
}

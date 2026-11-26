import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

final class PairFile {
    static Map<String, String> read(Path file) throws IOException {
        var rows = Files.readAllLines(file, StandardCharsets.UTF_8);
        var pairs = new LinkedHashMap<String, String>();
        for (int index = 0; index < rows.size(); index += 2) {
            String key = rows.get(index).strip();
            if (index + 1 == rows.size()) throw new IOException("Missing value after line " + (index + 1) + ".");
            String value = rows.get(index + 1).strip();
            if (key.isEmpty() || value.isEmpty()) throw new IOException("Blank entry at pair starting on line " + (index + 1) + ".");
            // TODO: Add this pair while rejecting duplicate keys.
        }
        return Collections.unmodifiableMap(pairs);
    }
}

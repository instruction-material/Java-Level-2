import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

final class JournalCounts {
    static Map<String, Integer> read(Path file) throws IOException {
        var totals = new TreeMap<String, Integer>();
        try (var input = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            for (String row = input.readLine(); row != null; row = input.readLine()) {
                String entry = row.strip().toLowerCase(Locale.ROOT);
                if (!entry.isEmpty()) totals.merge(entry, 1, Integer::sum);
            }
        }
        return totals;
    }
}

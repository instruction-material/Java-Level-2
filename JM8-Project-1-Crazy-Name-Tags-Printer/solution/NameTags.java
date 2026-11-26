import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

final class NameTags {
    static void write(Path file, String rawLabel) throws IOException {
        String label = rawLabel.strip();
        if (label.isEmpty()) throw new IllegalArgumentException("Use a nonblank fictional label.");
        int[] points = label.codePoints().toArray();
        var alternate = new StringBuilder();
        for (int index = 0; index < points.length; index += 2) alternate.appendCodePoint(points[index]);
        String reversed = new StringBuilder(label).reverse().toString();
        Files.write(file, List.of(label.toUpperCase(Locale.ROOT), alternate.toString(), reversed), StandardCharsets.UTF_8);
    }
}

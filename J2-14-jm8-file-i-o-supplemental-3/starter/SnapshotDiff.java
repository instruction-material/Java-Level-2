import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;

final class SnapshotDiff {
    static Set<String> added(Path before, Path after) throws IOException {
        Set<String> difference = new TreeSet<>(names(after));
        // TODO: Remove elements that the old snapshot already contained.
        return difference;
    }

    private static Set<String> names(Path file) throws IOException {
        var names = new TreeSet<String>();
        for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
            String name = line.strip();
            if (!name.isEmpty()) names.add(name);
        }
        return names;
    }
}

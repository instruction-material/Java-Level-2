import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

final class IntegerFile {
    static List<Integer> read(Path source) throws IOException {
        var lines = Files.readAllLines(source);
        if (lines.isEmpty()) throw new IllegalArgumentException("Missing count.");
        int count = Integer.parseInt(lines.getFirst().strip());
        if (count < 0 || count != lines.size() - 1) {
            throw new IllegalArgumentException("Wrong record count.");
        }
        var values = new ArrayList<Integer>();
        for (int i = 1; i < lines.size(); i++) {
            values.add(Integer.parseInt(lines.get(i).strip()));
        }
        return List.copyOf(values);
    }
}

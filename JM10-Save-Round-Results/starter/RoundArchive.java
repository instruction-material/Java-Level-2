import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

final class RoundArchive {
    private static final String VERSION = "ROUNDS-1";

    static void save(Path file, List<Board.Outcome> outcomes) throws IOException {
        var rows = new ArrayList<String>();
        rows.add(VERSION);
        for (var outcome : outcomes) {
            if (outcome == null || outcome == Board.Outcome.PLAYING) {
                throw new IllegalArgumentException("Save completed rounds only.");
            }
            rows.add(outcome.name());
        }
        var destination = file.toAbsolutePath();
        var staging = Files.createTempFile(destination.getParent(), "rounds-", ".tmp");
        try {
            Files.write(staging, rows, StandardCharsets.UTF_8);
            Files.move(staging, destination, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } finally {
            Files.deleteIfExists(staging);
        }
    }

    static List<Board.Outcome> load(Path file) throws IOException {
        List<String> rows;
        try {
            rows = Files.readAllLines(file, StandardCharsets.UTF_8);
        } catch (NoSuchFileException missing) {
            return List.of();
        }
        if (rows.isEmpty() || !VERSION.equals(rows.getFirst())) {
            throw new IOException("Unknown results format on line 1.");
        }
        var outcomes = new ArrayList<Board.Outcome>();
        for (int index = 1; index < rows.size(); index++) {
            try {
                var outcome = Board.Outcome.valueOf(rows.get(index));
                if (outcome == Board.Outcome.PLAYING) throw new IllegalArgumentException("Round still open.");
                // TODO: Keep the validated completed round.
            } catch (IllegalArgumentException invalid) {
                throw new IOException("Invalid outcome on line " + (index + 1) + ".");
            }
        }
        return List.copyOf(outcomes);
    }
}

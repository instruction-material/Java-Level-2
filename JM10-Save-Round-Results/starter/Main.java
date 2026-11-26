import java.nio.file.Files;
import java.util.List;

class Main {
    public static void main(String[] args) throws Exception {
        var path = Files.createTempFile("round-results-", ".txt");
        try {
            RoundArchive.save(path, List.of(Board.Outcome.X_WINS, Board.Outcome.TIE, Board.Outcome.O_WINS));
            System.out.println("Reopened: " + RoundArchive.load(path));
        } finally {
            Files.deleteIfExists(path);
        }
    }
}

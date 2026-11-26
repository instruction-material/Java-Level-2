import java.nio.file.Files;
import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        var path = Files.createTempFile("round-test-", ".txt");
        try {
            var expected = List.of(Board.Outcome.X_WINS, Board.Outcome.TIE);
            RoundArchive.save(path, expected);
            check(RoundArchive.load(path).equals(expected), "reopen completed rounds");
            try {
                RoundArchive.save(path, List.of(Board.Outcome.PLAYING));
                throw new AssertionError("accepted an unfinished round");
            } catch (IllegalArgumentException rejected) {
                check(RoundArchive.load(path).equals(expected), "rejected save preserves earlier rounds");
            }
            check(Files.readAllLines(path).equals(List.of("ROUNDS-1", "X_WINS", "TIE")), "exact file records");
            Files.writeString(path, "ROUNDS-1\nTIE\nunknown\n");
            try {
                RoundArchive.load(path);
                throw new AssertionError("accepted a broken record");
            } catch (java.io.IOException invalid) {
                check(invalid.getMessage().equals("Invalid outcome on line 3."), "specific diagnostic");
            }
            Files.delete(path);
            check(RoundArchive.load(path).isEmpty(), "missing file starts empty");
        } finally {
            Files.deleteIfExists(path);
        }
        System.out.println("Round archive checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

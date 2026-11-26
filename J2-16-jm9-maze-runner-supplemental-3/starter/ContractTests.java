import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ContractTests {
    public static void main(String[] args) {
        var result = MazeBatch.inspect(Map.of(
            "broken", Path.of("no-exit.txt"), "normal", Path.of("practice.txt"), "missing", Path.of("missing.txt")
        ));
        check(result.get("normal").equals("Ready at (1, 1)"), "valid board still reported");
        check(result.get("broken").equals("Maze needs one player start and one exit."), "invalid layout reported");
        check(result.get("missing").equals("Cannot read maze file."), "missing file is distinct");
        check(new ArrayList<>(result.keySet()).equals(List.of("broken", "missing", "normal")), "stable label order");
        check(MazeBatch.inspect(Map.of()).isEmpty(), "empty batch");
        System.out.println("Maze batch checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

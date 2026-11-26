import java.nio.file.Path;
import java.util.Map;

class Main {
    public static void main(String[] args) {
        var reports = MazeBatch.inspect(Map.of(
            "practice", Path.of("practice.txt"),
            "missing", Path.of("missing.txt"),
            "broken", Path.of("no-exit.txt")
        ));
        reports.forEach((label, report) -> System.out.println(label + ": " + report));
    }
}

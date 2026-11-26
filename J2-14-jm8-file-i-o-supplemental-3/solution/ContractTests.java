import java.nio.file.Files;
import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        try (var folder = new WorkbookFolder()) {
            var before = folder.file("before.txt");
            var after = folder.file("after.txt");
            Files.write(before, List.of("Storage", " Studio ", "Studio"));
            Files.write(after, List.of("Studio", "Workshop", "Atrium", " "));
            check(SnapshotDiff.added(before, after).toString().equals("[Atrium, Workshop]"), "sorted additions only");
            check(SnapshotDiff.added(after, before).toString().equals("[Storage]"), "reverse comparison");
            check(SnapshotDiff.added(before, before).isEmpty(), "equal snapshots");
            check(Files.readAllLines(before).size() == 3, "inputs stay unchanged");
        }
        System.out.println("Snapshot comparison checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

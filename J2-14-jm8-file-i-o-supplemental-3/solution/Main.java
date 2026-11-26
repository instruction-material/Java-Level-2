import java.nio.file.Files;
import java.util.List;

class Main {
    public static void main(String[] args) throws Exception {
        try (var folder = new WorkbookFolder()) {
            var before = folder.file("before.txt");
            var after = folder.file("after.txt");
            Files.write(before, List.of("Storage", "Studio"));
            Files.write(after, List.of("Studio", "Workshop"));
            System.out.println("Added: " + SnapshotDiff.added(before, after));
            System.out.println("Removed: " + SnapshotDiff.added(after, before));
        }
    }
}

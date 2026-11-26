import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        check(IntegerFile.read(Path.of("values.txt")).equals(List.of(12,-3,0,7)), "sample");
        var file = Files.createTempFile("java-level-2-values-", ".txt");
        try {
            Files.writeString(file, "0\n");
            check(IntegerFile.read(file).isEmpty(), "zero count");
            Files.writeString(file, "2\n5\n");
            try {
                IntegerFile.read(file);
                throw new AssertionError("truncated fixture accepted");
            } catch (IllegalArgumentException expected) {
                check(Files.readString(file).equals("2\n5\n"), "input preserved");
            }
        } finally {
            Files.deleteIfExists(file);
        }
        System.out.println("Checks passed.");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

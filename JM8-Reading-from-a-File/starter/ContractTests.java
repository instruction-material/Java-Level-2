import java.nio.file.Files;

class ContractTests {
    public static void main(String[] args) throws Exception {
        try (var folder = new WorkbookFolder()) {
            var path = folder.file("input.txt");
            Files.writeString(path, "4\n-2\n13\n");
            check(NumberFile.sum(path) == 15, "sum signed rows");
            Files.writeString(path, "");
            check(NumberFile.sum(path) == 0, "empty input");
            Files.writeString(path, "1\n\n");
            try {
                NumberFile.sum(path);
                throw new AssertionError("accepted blank row");
            } catch (IllegalArgumentException expected) {
                check(expected.getMessage().equals("Invalid number on line 2."), "numbered error");
            }
            try {
                NumberFile.sum(folder.file("missing.txt"));
                throw new AssertionError("accepted missing file");
            } catch (java.nio.file.NoSuchFileException expected) {
                check(expected.getFile() != null, "missing file differs from malformed");
            }
        }
        System.out.println("File reading checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

import java.nio.file.Files;

class ContractTests {
    public static void main(String[] args) throws Exception {
        try (var folder = new WorkbookFolder()) {
            var file = folder.file("sample.txt");
            Files.writeString(file, "Cedar\nfern\n cedar \n\nMoss bed\n");
            check(JournalCounts.read(file).toString().equals("{cedar=2, fern=1, moss bed=1}"), "combine repeated journal entries");
            Files.writeString(file, "");
            check(JournalCounts.read(file).isEmpty(), "empty journal");
            try {
                JournalCounts.read(folder.file("absent.txt"));
                throw new AssertionError("missing journal accepted");
            } catch (java.nio.file.NoSuchFileException expected) {
                check(expected.getFile() != null, "report missing journal");
            }
        }
        System.out.println("Journal checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

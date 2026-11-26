import java.nio.file.Files;

class ContractTests {
    public static void main(String[] args) throws Exception {
        try (var folder = new WorkbookFolder()) {
            var path = folder.file("pairs.txt");
            Files.writeString(path, "pen\nblue\nfolder\ngreen\n");
            var current = PairFile.read(path);
            check(current.toString().equals("{pen=blue, folder=green}"), "pair alternate lines in order");
            for (String[] sample : new String[][] {
                {"pen\nblue\npen\nred\n", "Duplicate key at line 3."},
                {"pen\n", "Missing value after line 1."},
                {"pen\n \n", "Blank entry at pair starting on line 1."}
            }) {
                Files.writeString(path, sample[0]);
                try {
                    PairFile.read(path);
                    throw new AssertionError("accepted invalid pair file");
                } catch (java.io.IOException expected) {
                    check(expected.getMessage().equals(sample[1]), "specific pair error");
                }
            }
            check(current.get("pen").equals("blue"), "failed load cannot change earlier result");
            Files.writeString(path, "");
            check(PairFile.read(path).isEmpty(), "empty file");
        }
        System.out.println("Pair file checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

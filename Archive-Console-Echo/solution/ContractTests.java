import java.io.BufferedReader;
import java.io.StringReader;

class ContractTests {
    public static void main(String[] args) throws Exception {
        try (var input = new BufferedReader(new StringReader("  red fox  \n\n"))) {
            check("  red fox  ".equals(ConsoleLine.read(input)), "sample");
            check("".equals(ConsoleLine.read(input)), "empty line is data");
            check(ConsoleLine.read(input) == null, "EOF has no line");
        }
        System.out.println("Checks passed.");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

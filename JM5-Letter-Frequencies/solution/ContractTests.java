import java.util.Map;

public class ContractTests {
    public static void main(String[] args) {
        check(LetterCounts.count("A!? a1\t").equals(Map.of((int) 'a', 2)), "case folds and counts letters only");
        check(LetterCounts.count("𐐀𐐨").equals(Map.of(0x10428, 2)), "count supplementary letters");
        check(LetterCounts.report("   42?!").equals("No letters found."), "report empty counts");
        check(LetterCounts.report("B a A b!").equals("a: 2\nb: 2\nMost frequent: a (2)"),
            "tied counts use code-point ordering");
        check(LetterCounts.report("abaa").endsWith("Most frequent: a (3)"), "track the highest count");
        System.out.println("Letter frequency checks passed");
    }
    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }
}

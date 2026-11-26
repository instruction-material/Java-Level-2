import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        check(QuizRound.score(List.of("Saturn","oxygen","Pacific"), List.of(" SATURN ", "argon", "pacific")) == 2, "sample");
        check(QuizRound.score(List.of("Mars"), List.of(" ")) == 0, "blank response");
        check(QuizRound.score(List.of(), List.of()) == 0, "no questions");
        try {
            QuizRound.score(List.of("Mars"), List.of());
            throw new AssertionError("partial round scored");
        } catch (IllegalArgumentException expected) {
            check(expected.getMessage().contains("all questions"), "missing response distinguished");
        }
        System.out.println("Checks passed.");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

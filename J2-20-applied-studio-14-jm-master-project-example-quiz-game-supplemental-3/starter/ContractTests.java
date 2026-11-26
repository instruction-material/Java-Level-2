import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        var input = List.of(new PracticeSet.Card("A","first"), new PracticeSet.Card("B","other"),
            new PracticeSet.Card("A","first"), new PracticeSet.Card("A","second"));
        check(PracticeSet.prompts(input,"A",2).equals(List.of("first","second")), "keep distinct prompts in original order");
        check(PracticeSet.prompts(input,"A",1).equals(List.of("first")), "single prompt");
        check(PracticeSet.prompts(input,"C",3).isEmpty() && input.size() == 4, "missing topic leaves source intact");
        try {
            PracticeSet.prompts(input,"A",0);
            throw new AssertionError("accepted zero limit");
        } catch (IllegalArgumentException invalid) {
            check(!invalid.getMessage().isBlank(), "specific limit error");
        }
        System.out.println("Quiz practice set checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

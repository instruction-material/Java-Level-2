import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        var report = QuizReview.summarize(List.of(new QuizReview.Attempt("Science", true),
            new QuizReview.Attempt("History", false), new QuizReview.Attempt("Science", false)));
        check(new QuizReview.Count(1,2).equals(report.get("Science")), "count both outcomes");
        check(new QuizReview.Count(0,1).equals(report.get("History")), "separate topic");
        check(report.keySet().stream().toList().equals(List.of("History","Science")), "ordered report");
        check(QuizReview.summarize(List.of()).isEmpty(), "empty report");
        System.out.println("Quiz summary checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

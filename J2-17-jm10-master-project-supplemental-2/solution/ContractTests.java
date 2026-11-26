import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        var review = new CheckReview();
        review.record("tie", false);
        review.record("restart", false);
        review.record("win", true);
        var initial = review.openChecks();
        check(initial.equals(List.of("restart","tie")), "sorted open checks");
        review.record(" tie ", true);
        check(review.openChecks().equals(List.of("restart")) && initial.size() == 2, "fresh report preserves earlier snapshot");
        try {
            review.record(" ", false);
            throw new AssertionError("accepted an unnamed check");
        } catch (IllegalArgumentException invalid) {
            check(review.openChecks().equals(List.of("restart")), "invalid label leaves state");
        }
        System.out.println("Review checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

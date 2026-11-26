public class ContractTests {
    public static void main(String[] args) {
        check(Quiz.getNumQuizzes() == 0, "initial counter starts at zero");
        Quiz empty = new Quiz();
        check(Quiz.getNumQuizzes() == 1, "no-argument constructor counts once");
        Quiz practice = new Quiz("Practice", 80);
        String[] questions = {"Which field is shared?", "Which belongs to one object?"};
        Quiz review = new Quiz("Review", 92, questions);
        questions[0] = "outside change";
        check(Quiz.getNumQuizzes() == 3, "three successful creations share one counter");
        check(empty.getScore() == 0 && practice.getScore() == 80 && review.getScore() == 92,
            "scores stay with their instances");
        check(practice.toString().endsWith("(none)"), "an empty question list has a readable summary");
        check(review.toString().endsWith("Which field is shared?"), "question summary is stable and copied");
        int[] scores = {0, 59, 60, 69, 70, 79, 80, 89, 90, 100};
        String[] expected = {"F", "F", "D", "D", "C", "C", "B", "B", "A", "A"};
        for (int i = 0; i < scores.length; i++) {
            check(expected[i].equals(new Quiz("Boundary", scores[i]).getLetterGrade()),
                "letter grade boundary at " + scores[i]);
        }
        rejects(() -> new Quiz("Invalid", -1));
        rejects(() -> new Quiz("Invalid", 101));
        check(Quiz.getNumQuizzes() == 13, "rejected creations do not increment the counter");
        System.out.println("Quiz checks passed");
    }

    private static void check(boolean result, String description) {
        if (!result) throw new AssertionError(description);
    }

    private static void rejects(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError("out-of-range score was accepted");
    }
}

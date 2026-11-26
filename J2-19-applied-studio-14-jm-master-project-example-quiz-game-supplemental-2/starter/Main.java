import java.util.List;

class Main {
    public static void main(String[] args) {
        var attempts = List.of(new QuizReview.Attempt("Science", true), new QuizReview.Attempt("History", false),
            new QuizReview.Attempt("Science", false), new QuizReview.Attempt("Science", true));
        QuizReview.summarize(attempts).forEach((topic, count) ->
            System.out.println(topic + ": " + count.correct() + "/" + count.total()));
    }
}

import java.util.Objects;

public final class Quiz {
    private static int numQuizzes;

    private final String name;
    private final int score;
    private final String[] questions;

    public Quiz() {
        this("Practice", 0);
    }

    public Quiz(String name, int score) {
        this(name, score, new String[0]);
    }

    public Quiz(String name, int score, String[] questions) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
        this.name = Objects.requireNonNull(name);
        this.score = score;
        this.questions = Objects.requireNonNull(questions).clone();
        numQuizzes++;
    }

    public static int getNumQuizzes() {
        return numQuizzes;
    }

    public int getScore() {
        return score;
    }

    public String getLetterGrade() {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    @Override
    public String toString() {
        String question = questions.length == 0 ? "(none)" : questions[0];
        return name + ": " + score + " (" + getLetterGrade() + "), first question: " + question;
    }
}

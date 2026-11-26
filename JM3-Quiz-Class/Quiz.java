import java.util.Objects;

public class Quiz {
    private int score;
    private String name;
    private String[] questions;

    //NOTE:
    //complete the project without this variable
    //then come back and add it later
    private static int numQuizzes;

    public Quiz() {
        this("", 0);
    }

    public Quiz(String n, int s) {
        this(n, s, new String[0]);
    }

    public Quiz(String n, int s, String[] q) {
        name = n;
        score = s;
        questions = Objects.requireNonNull(q).clone();
        numQuizzes++;
    }

    public int getScore() {
        return score;
    }

    public String getLetterGrade() {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    @Override
    public String toString() {
        String question = questions.length == 0
            ? "(none)"
            : questions[(int) (Math.random() * questions.length)];
        return name + " received a grade of " + getLetterGrade() +
            " on the quiz. A random question on the quiz was: " +
            question;
    }

    public static int getNumQuizzes() {
        return numQuizzes;
    }
}

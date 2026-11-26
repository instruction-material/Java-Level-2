import java.util.ArrayList;
import java.util.List;

public final class Question {
    private final String question;
    private final List<String> options;
    private final String correctAnswer;

    public Question(String question, List<String> options, String correctAnswer) {
        this.question = question;
        this.options = List.copyOf(options);
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getOptions() {
        return new ArrayList<>(options);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

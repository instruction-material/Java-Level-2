import java.util.List;

final class QuizRound {
    static int score(List<String> key, List<String> answers) {
        if (key.size() != answers.size()) throw new IllegalArgumentException("Finish all questions.");
        int total = 0;
        for (int i = 0; i < key.size(); i++) {
            String answer = answers.get(i).strip();
            if (!answer.isEmpty() && answer.equalsIgnoreCase(key.get(i).strip())) {
                total++;
            }
        }
        return total;
    }
}

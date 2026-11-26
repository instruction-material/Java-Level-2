import java.util.List;
import java.util.Map;
import java.util.TreeMap;

final class QuizReview {
    record Attempt(String topic, boolean correct) {}
    record Count(int correct, int total) {}

    static Map<String, Count> summarize(List<Attempt> attempts) {
        var totals = new TreeMap<String, Count>();
        for (var attempt : attempts) {
            var previous = totals.getOrDefault(attempt.topic(), new Count(0, 0));
            totals.put(attempt.topic(), new Count(previous.correct() + (attempt.correct() ? 1 : 0), previous.total() + 1));
        }
        return totals;
    }
}

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

final class PracticeSet {
    record Card(String topic, String prompt) {}

    static List<String> prompts(List<Card> cards, String topic, int limit) {
        if (limit < 1) throw new IllegalArgumentException("Use a positive question limit.");
        var seen = new HashSet<String>();
        var chosen = new ArrayList<String>();
        for (var card : cards) {
            // TODO: Add the first matching occurrence and stop when the set is full.
        }
        return List.copyOf(chosen);
    }
}

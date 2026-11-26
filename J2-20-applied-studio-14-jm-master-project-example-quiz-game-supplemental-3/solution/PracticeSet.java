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
            if (card.topic().equals(topic) && seen.add(card.prompt())) {
                chosen.add(card.prompt());
                if (chosen.size() == limit) break;
            }
        }
        return List.copyOf(chosen);
    }
}

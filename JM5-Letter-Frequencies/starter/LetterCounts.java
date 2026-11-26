import java.util.Map;
import java.util.TreeMap;

public final class LetterCounts {
    private LetterCounts() {}

    public static Map<Integer, Integer> count(String text) {
        Map<Integer, Integer> counts = new TreeMap<>();
        // TODO: Count each normalized letter.
        return counts;
    }

    public static String report(String text) {
        Map<Integer, Integer> counts = count(text);
        if (counts.isEmpty()) return "No letters found.";

        StringBuilder lines = new StringBuilder();
        Map.Entry<Integer, Integer> largest = null;
        for (var entry : counts.entrySet()) {
            lines.appendCodePoint(entry.getKey()).append(": ").append(entry.getValue()).append('\n');
            if (largest == null || entry.getValue() > largest.getValue()) largest = entry;
        }
        return lines.append("Most frequent: ").appendCodePoint(largest.getKey())
            .append(" (").append(largest.getValue()).append(")").toString();
    }
}

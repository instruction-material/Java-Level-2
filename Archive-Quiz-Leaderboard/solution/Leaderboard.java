import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

final class Leaderboard {
    record Entry(String alias, int score) {
        Entry {
            if (alias.isBlank() || score < 0) throw new IllegalArgumentException("Use a valid result.");
        }

        @Override
        public String toString() {
            return alias + " (" + score + ")";
        }
    }

    static List<Entry> top(List<Entry> entries, int limit) {
        if (limit < 0) throw new IllegalArgumentException("Use a nonnegative limit.");
        var sorted = new ArrayList<>(entries);
        sorted.sort(Comparator.comparingInt(Entry::score).reversed());
        return List.copyOf(sorted.subList(0, Math.min(limit, sorted.size())));
    }
}

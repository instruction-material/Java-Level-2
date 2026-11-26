import java.util.Map;
import java.util.TreeMap;

final class Spending {
    private final Map<String, Long> totals = new TreeMap<>();

    boolean charge(String category, long cents) {
        String name = category.strip();
        long previous = totals.getOrDefault(name, 0L);
        if (name.isEmpty() || cents <= 0 || cents > Long.MAX_VALUE - previous) return false;
        totals.put(name, previous + cents);
        return true;
    }

    Map<String, Long> snapshot() {
        return new TreeMap<>(totals);
    }
}

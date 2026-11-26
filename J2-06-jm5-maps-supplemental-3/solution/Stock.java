import java.util.Map;
import java.util.TreeMap;

public final class Stock {
    private final Map<String, Integer> units;

    public Stock(Map<String, Integer> initial) {
        Map<String, Integer> checked = Map.copyOf(initial);
        if (checked.values().stream().anyMatch(count -> count < 0)) {
            throw new IllegalArgumentException("Stock must be nonnegative");
        }
        units = new TreeMap<>(checked);
    }

    public boolean reserve(Map<String, Integer> requested) {
        Map<String, Integer> order = Map.copyOf(requested);
        for (var item : order.entrySet()) {
            Integer available = units.get(item.getKey());
            if (available == null || item.getValue() <= 0 || item.getValue() > available) {
                return false;
            }
        }
        order.forEach((key, amount) -> units.put(key, units.get(key) - amount));
        return true;
    }

    public String inventory() {
        return units.toString();
    }
}

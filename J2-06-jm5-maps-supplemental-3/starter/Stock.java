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
        return false; // TODO: Validate the whole request, then apply it.
    }

    public String inventory() {
        return units.toString();
    }
}

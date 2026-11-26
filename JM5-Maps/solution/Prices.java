import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public final class Prices {
    private final Map<String, Integer> prices = new HashMap<>();

    public void change(String item, int cents) {
        prices.put(item, cents);
    }

    public int priceFor(String item, int defaultCents) {
        return prices.getOrDefault(item, defaultCents);
    }

    public boolean addNew(String item, int cents) {
        return prices.putIfAbsent(item, cents) == null;
    }

    public Integer remove(String item) {
        return prices.remove(item);
    }

    public int size() {
        return prices.size();
    }

    public String inventory() {
        return new TreeMap<>(prices).toString();
    }
}

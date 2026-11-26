import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

final class CheckReview {
    private final Map<String, Boolean> results = new TreeMap<>();

    void record(String label, boolean passed) {
        String name = label.strip();
        if (name.isEmpty()) throw new IllegalArgumentException("Use a named check.");
        results.put(name, passed);
    }

    List<String> openChecks() {
        var open = new ArrayList<String>();
        for (var entry : results.entrySet()) {
            if (!entry.getValue()) open.add(entry.getKey());
        }
        return List.copyOf(open);
    }
}

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
        // TODO: Collect the labels whose recorded value is false.
        return List.of();
    }
}

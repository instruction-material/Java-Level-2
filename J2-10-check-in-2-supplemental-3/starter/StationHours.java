import java.util.Map;
import java.util.TreeMap;

final class StationHours {
    static Map<String, Integer> report(int sharedHour, Map<String, Integer> offsets) {
        if (sharedHour < 0 || sharedHour > 23) {
            throw new IllegalArgumentException("Shared hour must be from 0 to 23.");
        }
        var localHours = new TreeMap<String, Integer>();
        // TODO: Convert each station entry and add it to the sorted report.
        return localHours;
    }
}

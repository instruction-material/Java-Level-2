import java.util.Map;
import java.util.TreeMap;

final class StationHours {
    static Map<String, Integer> report(int sharedHour, Map<String, Integer> offsets) {
        if (sharedHour < 0 || sharedHour > 23) {
            throw new IllegalArgumentException("Shared hour must be from 0 to 23.");
        }
        var localHours = new TreeMap<String, Integer>();
        for (var station : offsets.entrySet()) {
            long adjustedHour = (long) sharedHour + station.getValue();
            localHours.put(station.getKey(), Math.floorMod(adjustedHour, 24));
        }
        return localHours;
    }
}

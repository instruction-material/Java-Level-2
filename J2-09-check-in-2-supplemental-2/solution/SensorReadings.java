import java.util.HashMap;
import java.util.Map;

final class SensorReadings {
    private final Map<String, Integer> readings = new HashMap<>();

    boolean record(String command) {
        String[] fields = command.strip().split("\s+");
        if (fields.length != 2) return false;
        int value;
        try {
            value = Integer.parseInt(fields[1]);
        } catch (NumberFormatException malformed) {
            return false;
        }
        readings.put(fields[0], value);
        return true;
    }

    Integer reading(String sensor) {
        return readings.get(sensor);
    }
}

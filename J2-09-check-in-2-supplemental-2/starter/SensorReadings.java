import java.util.HashMap;
import java.util.Map;

final class SensorReadings {
    private final Map<String, Integer> readings = new HashMap<>();

    boolean record(String command) {
        String[] fields = command.strip().split("\s+");
        if (fields.length != 2) return false;
        // TODO: Parse and validate the reading before replacing any value.
        return false;
    }

    Integer reading(String sensor) {
        return readings.get(sensor);
    }
}

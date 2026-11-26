import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class RomanClock extends HourClock {
    private static final String[] NUMERALS = {
        "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII"
    };
    private final Map<String, Integer> values = new LinkedHashMap<>();

    public RomanClock() {
        this(1);
    }

    public RomanClock(int hour) {
        super(false, hour);
    }

    String numeralForHour() {
        return NUMERALS[getHour() - 1];
    }

    @Override
    public void displayTime() {
        System.out.println("Roman Time: " + numeralForHour());
    }

    public Map<String, Integer> updateMap() {
        for (int index = 0; index < NUMERALS.length; index++) {
            values.putIfAbsent(NUMERALS[index], index + 1);
        }
        values.putIfAbsent("L", 50);
        return Collections.unmodifiableMap(new LinkedHashMap<>(values));
    }
}

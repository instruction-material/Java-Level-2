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
        // TODO: Find the symbol for the active hour.
        return "?";
    }

    @Override
    public void displayTime() {
        // TODO: Replace the inherited display with the Roman symbol.
        super.displayTime();
    }

    public Map<String, Integer> updateMap() {
        // TODO: Build an ordered map and return a read-only snapshot.
        return Map.of();
    }
}

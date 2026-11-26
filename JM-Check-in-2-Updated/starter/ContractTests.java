import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ContractTests {
    public static void main(String[] args) {
        var roman = new RomanClock(8);
        check("VIII".equals(roman.numeralForHour()), "Roman eight");
        var map = roman.updateMap();
        check(map.equals(Map.ofEntries(
            Map.entry("I", 1), Map.entry("II", 2), Map.entry("III", 3), Map.entry("IV", 4),
            Map.entry("V", 5), Map.entry("VI", 6), Map.entry("VII", 7), Map.entry("VIII", 8),
            Map.entry("IX", 9), Map.entry("X", 10), Map.entry("XI", 11), Map.entry("XII", 12),
            Map.entry("L", 50)
        )), "complete symbol table");
        check(new ArrayList<>(map.keySet()).equals(List.of("I", "II", "III", "IV", "V", "VI",
            "VII", "VIII", "IX", "X", "XI", "XII", "L")), "printed order");
        check(roman.updateMap().size() == map.size(), "conditional insert is repeatable");
        try {
            map.put("L", 20);
            throw new AssertionError("map snapshot was mutable");
        } catch (UnsupportedOperationException expected) {
            check(roman.updateMap().get("L") == 50, "clock keeps original symbol");
        }
        try {
            roman.setHour(13);
            throw new AssertionError("accepted invalid hour");
        } catch (IllegalArgumentException expected) {
            check(roman.getHour() == 8, "failed change preserves state");
        }
        roman.setHour(12);
        roman.tick();
        check("I".equals(roman.numeralForHour()), "12-hour rollover");
        var midnight = new HourClock(true, 23);
        midnight.tick();
        check(midnight.getHour() == 0, "24-hour rollover");
        System.out.println("Clock and map checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

import java.util.Map;

class Main {
    public static void main(String[] args) {
        var noon = new HourClock(false, 12);
        var late = new HourClock(true, 23);
        noon.tick();
        late.tick();
        System.out.println("12-hour rollover: " + noon.getHour());
        System.out.println("24-hour rollover: " + late.getHour());

        var roman = new RomanClock(8);
        HourClock clock = roman;
        clock.displayTime();
        Map<String, Integer> values = roman.updateMap();
        for (var entry : values.entrySet()) {
            System.out.println(entry.getKey() + " maps to: " + entry.getValue());
        }
        try {
            clock.setHour(13);
        } catch (IllegalArgumentException rejected) {
            System.out.println(rejected.getMessage());
            System.out.println("Still at: " + clock.getHour());
        }
        clock.setHour(9);
        clock.displayTime();
    }
}

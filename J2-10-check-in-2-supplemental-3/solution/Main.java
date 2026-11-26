import java.util.Map;

class Main {
    public static void main(String[] args) {
        Map<String, Integer> offsets = Map.of("Ridge", -3, "Harbor", 4);
        System.out.println("At 23: " + StationHours.report(23, offsets));
        System.out.println("At 1: " + StationHours.report(1, offsets));
    }
}

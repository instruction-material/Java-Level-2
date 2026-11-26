import java.util.Map;

class ContractTests {
    public static void main(String[] args) {
        var offsets = Map.of("Ridge", -3, "Harbor", 4);
        check(StationHours.report(23, offsets).toString().equals("{Harbor=3, Ridge=20}"), "positive midnight rollover and order");
        check(StationHours.report(1, offsets).toString().equals("{Harbor=5, Ridge=22}"), "negative midnight rollover");
        var report = StationHours.report(9, offsets);
        report.put("Ridge", 0);
        check(StationHours.report(9, offsets).get("Ridge") == 6, "report mutation leaves offsets unchanged");
        check(StationHours.report(0, Map.of()).isEmpty(), "empty station table");
        check(StationHours.report(23, Map.of("Edge", Integer.MAX_VALUE)).get("Edge") == 6, "sum does not overflow");
        try {
            StationHours.report(24, offsets);
            throw new AssertionError("accepted invalid shared hour");
        } catch (IllegalArgumentException expected) {
            check(offsets.get("Harbor") == 4, "rejected report preserves input");
        }
        System.out.println("Station report checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

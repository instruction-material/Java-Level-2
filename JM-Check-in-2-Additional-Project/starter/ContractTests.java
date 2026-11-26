class ContractTests {
    public static void main(String[] args) {
        var noon = new MinuteClock(false, 12, 58);
        HourClock throughParent = noon;
        throughParent.tick();
        check(noon.getHour() == 12 && noon.getMinute() == 59, "normal minute advances once");
        throughParent.tick();
        check(noon.getHour() == 1 && noon.getMinute() == 0, "12-hour minute rollover");
        var midnight = new MinuteClock(true, 23, 59);
        midnight.tick();
        check(midnight.getHour() == 0 && midnight.getMinute() == 0, "midnight rollover");
        var other = new MinuteClock(false, 4, 0);
        check(other.getHour() == 4 && other.getMinute() == 0, "independent clock");
        try {
            new MinuteClock(false, 1, 60);
            throw new AssertionError("invalid minute accepted");
        } catch (IllegalArgumentException expected) {
            check("Minute outside clock range.".equals(expected.getMessage()), "specific minute failure");
        }
        System.out.println("Minute rollover checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

class ContractTests {
    public static void main(String[] args) {
        var readings = new SensorReadings();
        check(readings.record("east 7"), "accept valid reading");
        check(readings.record("west 9"), "another sensor");
        check(!readings.record("east broken") && readings.reading("east") == 7, "preserve value on failed parse");
        check(!readings.record("") && !readings.record("east") && !readings.record("east 1 2"), "reject malformed commands");
        check(!readings.record("new 9999999999999") && readings.reading("new") == null, "overflow and missing sensor");
        check(readings.record(" east -2 ") && readings.reading("east") == -2, "retry successfully");
        check(readings.reading("west") == 9, "sensor readings are independent");
        System.out.println("Sensor recovery checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

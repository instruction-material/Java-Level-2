class Main {
    public static void main(String[] args) {
        var readings = new SensorReadings();
        readings.record("east 7");
        System.out.println("Rejected: " + !readings.record("east broken"));
        System.out.println("After rejection: " + readings.reading("east"));
        readings.record("east -2");
        System.out.println("After retry: " + readings.reading("east"));
    }
}

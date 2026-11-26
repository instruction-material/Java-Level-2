class TemperatureBatch implements Runnable {
    private final int[] readings;
    private String summary;

    TemperatureBatch(int[] readings) {
        this.readings = readings.clone();
    }

    @Override
    public void run() {
        if (readings.length == 0) {
            summary = "No readings.";
            return;
        }
        // TODO: Compare the readings and store a minimum and maximum.
        summary = "Report pending.";
    }

    String summary() {
        return summary;
    }
}

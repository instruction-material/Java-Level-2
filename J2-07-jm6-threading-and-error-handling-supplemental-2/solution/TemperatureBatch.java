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
        int min = readings[0];
        int max = readings[0];
        for (int index = 1; index < readings.length; index++) {
            min = Math.min(min, readings[index]);
            max = Math.max(max, readings[index]);
        }
        summary = "min " + min + ", max " + max;
    }

    String summary() {
        return summary;
    }
}

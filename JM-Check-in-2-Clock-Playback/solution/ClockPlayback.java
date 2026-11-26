import java.util.function.IntConsumer;

final class ClockPlayback implements Runnable {
    private final HourClock clock;
    private final int records;
    private final long delayMillis;
    private final IntConsumer report;

    ClockPlayback(int startHour, int records, long delayMillis, IntConsumer report) {
        if (records < 1 || delayMillis < 0) {
            throw new IllegalArgumentException("Use a positive record count and a nonnegative delay.");
        }
        clock = new HourClock(false, startHour);
        this.records = records;
        this.delayMillis = delayMillis;
        this.report = report;
    }

    @Override
    public void run() {
        for (int index = 0; index < records && !Thread.currentThread().isInterrupted(); index++) {
            report.accept(clock.getHour());
            if (index + 1 == records) return;
            clock.tick();
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException interrupted) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

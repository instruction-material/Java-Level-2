import java.util.Locale;

public final class MinuteClock extends HourClock {
    private int minute;

    public MinuteClock(boolean twentyFourHour) {
        this(twentyFourHour, 1, 0);
    }

    public MinuteClock(boolean twentyFourHour, int hour, int minute) {
        super(twentyFourHour, hour);
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Minute outside clock range.");
        }
        this.minute = minute;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public void tick() {
        // TODO: Advance a minute and call super.tick() only at the hour boundary.
    }

    @Override
    public void displayTime() {
        // TODO: Display the two-digit minute as part of the time.
        super.displayTime();
    }
}

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
        if (minute == 59) {
            minute = 0;
            super.tick();
        } else {
            minute++;
        }
    }

    @Override
    public void displayTime() {
        System.out.printf(Locale.ROOT, "Time: %d:%02d%n", getHour(), minute);
    }
}

public class HourClock {
    private final boolean twentyFourHour;
    private int hour;

    public HourClock(boolean twentyFourHour) {
        this(twentyFourHour, 1);
    }

    public HourClock(boolean twentyFourHour, int hour) {
        this.twentyFourHour = twentyFourHour;
        setHour(hour);
    }

    public final void setHour(int hour) {
        int minimum = twentyFourHour ? 0 : 1;
        int maximum = twentyFourHour ? 23 : 12;
        if (hour < minimum || hour > maximum) {
            throw new IllegalArgumentException("Hour outside clock range.");
        }
        this.hour = hour;
    }

    public final int getHour() {
        return hour;
    }

    public final boolean getIsMilitary() {
        return twentyFourHour;
    }

    public void tick() {
        if (twentyFourHour) {
            hour = (hour + 1) % 24;
        } else {
            hour = hour % 12 + 1;
        }
    }

    public void displayTime() {
        System.out.println("Time: " + getHour() + ":00");
    }
}

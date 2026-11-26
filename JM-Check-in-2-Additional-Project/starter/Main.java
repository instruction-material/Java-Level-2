class Main {
    public static void main(String[] args) {
        HourClock ordinary = new MinuteClock(false, 12, 58);
        ordinary.displayTime();
        ordinary.tick();
        ordinary.displayTime();
        ordinary.tick();
        ordinary.displayTime();
        HourClock military = new MinuteClock(true, 23, 59);
        military.tick();
        military.displayTime();
    }
}

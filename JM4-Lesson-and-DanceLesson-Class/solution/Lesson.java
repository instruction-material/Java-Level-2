public class Lesson implements Comparable<Lesson> {
    private final int minutes;
    private final String subject;

    public Lesson(int minutes, String subject) {
        if (minutes <= 0) throw new IllegalArgumentException("Duration must be positive");
        this.minutes = minutes;
        this.subject = subject;
    }

    @Override
    public final int compareTo(Lesson other) {
        return Integer.compare(minutes, other.minutes);
    }

    @Override
    public String toString() {
        return subject + ", " + minutes + " minutes";
    }
}

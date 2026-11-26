public final class Attempt {
    private final String label;
    private final int points;
    private final int seconds;

    public Attempt(String label, int points, int seconds) {
        this.label = label;
        this.points = points;
        this.seconds = seconds;
    }

    public int getPoints() {
        return points;
    }

    public int getSeconds() {
        return seconds;
    }

    @Override
    public String toString() {
        return label;
    }
}

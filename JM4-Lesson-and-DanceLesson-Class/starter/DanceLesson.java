public final class DanceLesson extends Lesson {
    private final String style;
    private final int difficulty;

    public DanceLesson(int minutes, String style, int difficulty) {
        super(minutes, "Dance");
        this.style = style;
        this.difficulty = difficulty;
    }

    public boolean easierThan(DanceLesson other) {
        return false; // TODO: Compare difficulty ratings.
    }

    @Override
    public String toString() {
        return super.toString(); // TODO: Include the style and difficulty.
    }
}

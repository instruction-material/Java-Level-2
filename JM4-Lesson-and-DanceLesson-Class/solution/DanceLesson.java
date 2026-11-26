public final class DanceLesson extends Lesson {
    private final String style;
    private final int difficulty;

    public DanceLesson(int minutes, String style, int difficulty) {
        super(minutes, "Dance");
        this.style = style;
        this.difficulty = difficulty;
    }

    public boolean easierThan(DanceLesson other) {
        return other != null && difficulty < other.difficulty;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + style + " (difficulty " + difficulty + ")";
    }
}

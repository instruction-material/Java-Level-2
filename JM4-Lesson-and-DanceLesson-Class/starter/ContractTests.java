import java.util.Arrays;

public class ContractTests {
    public static void main(String[] args) {
        Lesson art = new Lesson(30, "Art");
        DanceLesson ballet = new DanceLesson(60, "ballet", 2);
        DanceLesson jazz = new DanceLesson(45, "jazz", 3);
        Lesson parent = ballet;
        check(parent.toString().equals("Dance, 60 minutes, ballet (difficulty 2)"),
            "parent view displays dance details");
        check(ballet.easierThan(jazz) && !jazz.easierThan(ballet), "difficulty may disagree with duration");
        check(!ballet.easierThan(new DanceLesson(45, "tap", 2)) && !ballet.easierThan(null),
            "difficulty ties and missing entries are not easier");
        Lesson[] list = {ballet, art, jazz};
        Arrays.sort(list);
        check(Arrays.equals(list, new Lesson[]{art, jazz, ballet}), "mixed lessons sort using the parent contract");
        check(jazz.compareTo(new Lesson(45, "Music")) == 0, "duration ties across lesson types");
        System.out.println("Dance lesson checks passed");
    }
    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }
}

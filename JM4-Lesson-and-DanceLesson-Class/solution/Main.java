import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        DanceLesson ballet = new DanceLesson(60, "ballet", 2);
        DanceLesson jazz = new DanceLesson(45, "jazz", 3);
        Lesson[] lessons = {ballet, new Lesson(30, "Art"), jazz};
        Arrays.sort(lessons);
        for (Lesson lesson : lessons) {
            System.out.println(lesson);
        }
        System.out.println("Ballet is easier: " + ballet.easierThan(jazz));
    }
}

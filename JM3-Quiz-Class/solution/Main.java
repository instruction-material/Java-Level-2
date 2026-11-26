public class Main {
    public static void main(String[] args) {
        Quiz review = new Quiz("Review", 92, new String[]{"Which values belong to an instance?"});
        Quiz practice = new Quiz("Practice", 80);
        System.out.println(review);
        System.out.println(practice);
        System.out.println("Quizzes created: " + Quiz.getNumQuizzes());
    }
}

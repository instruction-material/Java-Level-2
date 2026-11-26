import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class ContractTests {
    public static void main(String[] args) {
        String game = play("w\nD\n d \n");
        check(game.contains("Congratulations! You've reached the exit!"), "two right moves reach the exit");
        check(game.contains("# PE#") && game.contains("#  P#"), "intermediate and final position");
        check(game.contains("Invalid move. Try again."), "wall rejected");
        check(play("").contains("Input ended."), "closed input exits");
        check(play("left\nq\n").contains("Game ended."), "quit command");
        System.out.println("Untimed maze checks passed");
    }

    private static String play(String input) {
        var bytes = new ByteArrayOutputStream();
        var previous = System.out;
        try (var scanner = new Scanner(input); var capture = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            System.setOut(capture);
            new MazeGame("practice.txt").runGame(scanner);
        } finally {
            System.setOut(previous);
        }
        return bytes.toString(StandardCharsets.UTF_8);
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

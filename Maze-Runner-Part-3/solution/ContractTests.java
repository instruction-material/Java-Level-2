import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class ContractTests {
    public static void main(String[] args) {
        try (var count = new Timer(3, 0)) {
            count.run();
            check(count.getTime() == 0, "countdown reaches zero");
        }
        check(play("", new Timer(30, 1000)).contains("Input ended."), "closed input");
        check(play("Q\n", new Timer(30, 1000)).contains("Game ended."), "quit");
        check(play("D\nD\n", new Timer(30, 1000)).contains("You've reached the exit!"), "win");
        String expired = play("D\nD\n", new Timer(0, 1000));
        check(expired.contains("Time's up!") && !expired.contains("You've reached the exit!"), "expired move is rejected");
        var timer = new Timer(30, 1000);
        timer.init();
        Thread.currentThread().interrupt();
        timer.close();
        check(!timer.isRunning() && Thread.interrupted(), "interrupt and join preserve the caller's flag");
        System.out.println("Timed maze checks passed");
    }

    private static String play(String input, Timer timer) {
        var bytes = new ByteArrayOutputStream();
        var previous = System.out;
        try (var scanner = new Scanner(input); var capture = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            System.setOut(capture);
            new MazeGame("practice.txt").runGame(scanner, timer);
        } finally {
            System.setOut(previous);
        }
        check(!timer.isRunning(), "worker joined when the game exits");
        return bytes.toString(StandardCharsets.UTF_8);
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

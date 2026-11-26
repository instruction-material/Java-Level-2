import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class ContractTests {
    public static void main(String[] args) {
        String shortBoard = args[0];
        verify(shortBoard, "", new Timer(30, 1000), "Input ended.");
        verify(shortBoard, "wrong\nq\n", new Timer(30, 1000), "Invalid move. Try again.", "Game ended.");
        verify(shortBoard, "w\nd\n", new Timer(30, 1000), "Invalid move. Try again.", "You've reached the exit!");
        verify(shortBoard, "d\n", new Timer(0, 1000), "Time's up!");

        var timer = new Timer(30, 1000);
        timer.init();
        Thread.currentThread().interrupt();
        timer.close();
        check(!timer.isRunning(), "An interrupted owner still joins its worker.");
        check(Thread.interrupted(), "The interrupted owner retains its interrupt flag.");

        for (int index = 1; index < args.length; index++) {
            try {
                new Maze(args[index]);
                throw new AssertionError("Accepted an invalid maze");
            } catch (IllegalArgumentException expected) {
                check(!expected.getMessage().isBlank(), "Malformed boards report a reason.");
            }
        }

        System.out.println("Timed maze exit and cleanup checks passed");
    }

    private static void verify(String path, String commands, Timer timer, String... outcomes) {
        var bytes = new ByteArrayOutputStream();
        var console = System.out;
        try (var input = new Scanner(commands); var output = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            System.setOut(output);
            new MazeGame(path).runGame(input, timer);
        } finally {
            System.setOut(console);
        }
        String transcript = bytes.toString(StandardCharsets.UTF_8);
        for (var outcome : outcomes) {
            check(transcript.contains(outcome), "Missing " + outcome);
        }
        if (outcomes[0].equals("Time's up!")) {
            check(!transcript.contains("You've reached the exit!"), "An expired game cannot accept a winning move.");
        }
        check(!timer.isRunning(), "Countdown is joined at every exit.");
    }

    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}

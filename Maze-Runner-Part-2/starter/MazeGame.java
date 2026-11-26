import java.util.Locale;
import java.util.Scanner;

public final class MazeGame {
    private final Maze maze;
    private final Player player;

    public MazeGame(String mazeFilePath) {
        maze = new Maze(mazeFilePath);
        player = new Player(maze.getPlayerStartX(), maze.getPlayerStartY());
    }

    public void runGame() {
        try (var input = new Scanner(System.in)) {
            runGame(input);
        }
    }

    void runGame(Scanner input) {
        while (true) {
            maze.print(player);
            System.out.println("Enter a move (WASD), or Q to quit:");
            if (!input.hasNextLine()) {
                System.out.println("Input ended.");
                return;
            }
            String move = input.nextLine().strip().toUpperCase(Locale.ROOT);
            if (move.equals("Q")) {
                System.out.println("Game ended.");
                return;
            }
            if (!movePlayer(move)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            if (maze.isExit(player.getX(), player.getY())) {
                maze.print(player);
                System.out.println("Congratulations! You've reached the exit!");
                return;
            }
        }
    }

    private boolean movePlayer(String move) {
        int x = player.getX();
        int y = player.getY();
        switch (move) {
            case "W" -> y--;
            case "A" -> x--;
            case "S" -> y++;
            case "D" -> x++;
            default -> { return false; }
        }
        // TODO: Apply the legal candidate position and report whether the move succeeded.
        return false;
    }
}

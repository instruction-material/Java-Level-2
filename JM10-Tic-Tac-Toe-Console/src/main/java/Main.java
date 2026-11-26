import java.util.Scanner;

public final class Main {
    public static void main(String[] args) {
        try (var input = new Scanner(System.in)) {
            play(input);
        }
    }

    private static void play(Scanner input) {
        var game = new TicTacToe();
        game.printBoard();
        while (true) {
            System.out.println("Player " + game.getCurrentPlayer() + ", enter row and column (1–3), or Q to quit:");
            if (!input.hasNextLine()) {
                System.out.println("Input ended.");
                return;
            }
            String command = input.nextLine().strip();
            if (command.equalsIgnoreCase("Q")) {
                System.out.println("Game ended.");
                return;
            }
            if (!place(game, command)) {
                System.out.println("Choose an open cell with two numbers from 1 to 3.");
                continue;
            }
            game.printBoard();
            if (game.checkForWin()) {
                System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                return;
            }
            if (game.isBoardFull()) {
                System.out.println("The game is a tie!");
                return;
            }
            game.changePlayer();
        }
    }

    private static boolean place(TicTacToe game, String command) {
        String[] cells = command.split("\\s+");
        if (cells.length != 2) return false;
        try {
            int row = Integer.parseInt(cells[0]);
            int column = Integer.parseInt(cells[1]);
            return row >= 1 && row <= 3 && column >= 1 && column <= 3 && game.placeMark(row - 1, column - 1);
        } catch (NumberFormatException malformed) {
            return false;
        }
    }
}

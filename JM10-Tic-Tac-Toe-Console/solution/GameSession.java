import java.util.function.Consumer;

final class GameSession {
    static void play(Board board, MoveSource moves, Consumer<String> show) {
        show.accept(board.view());
        while (board.outcome() == Board.Outcome.PLAYING) {
            show.accept("Move for " + board.next() + " (row column, or Q):");
            String line = moves.nextLine();
            if (line == null || line.strip().equalsIgnoreCase("Q")) {
                show.accept("Round ended.");
                return;
            }
            if (!tryMove(board, line)) {
                show.accept("Choose an open cell with two numbers from 1 to 3.");
                continue;
            }
            show.accept(board.view());
        }
        switch (board.outcome()) {
            case X_WINS -> show.accept("Winner: X");
            case O_WINS -> show.accept("Winner: O");
            case TIE -> show.accept("Tie.");
            default -> throw new IllegalStateException("Round is still in progress.");
        }
    }

    private static boolean tryMove(Board board, String command) {
        String[] fields = command.strip().split("\s+");
        if (fields.length != 2) return false;
        try {
            int row = Integer.parseInt(fields[0]);
            int column = Integer.parseInt(fields[1]);
            return row >= 1 && row <= 3 && column >= 1 && column <= 3 && board.play(row - 1, column - 1);
        } catch (NumberFormatException malformed) {
            return false;
        }
    }
}

import java.util.List;
import java.util.Locale;

final class RouteReplay {
    record Report(int accepted, int rejected, boolean escaped) {
    }

    static Report run(Maze maze, List<String> commands) {
        int x = maze.getPlayerStartX();
        int y = maze.getPlayerStartY();
        int accepted = 0;
        int rejected = 0;
        for (String raw : commands) {
            int candidateX = x;
            int candidateY = y;
            switch (raw.strip().toUpperCase(Locale.ROOT)) {
                case "W" -> candidateY--;
                case "A" -> candidateX--;
                case "S" -> candidateY++;
                case "D" -> candidateX++;
                default -> {
                    rejected++;
                    continue;
                }
            }
            if (!maze.isValidMove(candidateX, candidateY)) {
                rejected++;
                continue;
            }
            // TODO: Update the position, count the move, and finish if this is the exit.
        }
        return new Report(accepted, rejected, false);
    }
}

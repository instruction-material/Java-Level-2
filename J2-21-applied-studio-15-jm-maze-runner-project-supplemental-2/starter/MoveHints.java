import java.util.ArrayList;
import java.util.List;

final class MoveHints {
    static List<String> options(Maze maze, int x, int y) {
        var moves = new ArrayList<String>();
        if (!maze.isValidMove(x,y)) return moves;
        // TODO: Test the four neighboring cells and record their legal directions.
        return moves;
    }
}

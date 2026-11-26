import java.util.ArrayList;
import java.util.List;

final class MoveHints {
    static List<String> options(Maze maze, int x, int y) {
        var moves = new ArrayList<String>();
        if (!maze.isValidMove(x,y)) return moves;
        if (maze.isValidMove(x,y-1)) moves.add("W");
        if (maze.isValidMove(x-1,y)) moves.add("A");
        if (maze.isValidMove(x,y+1)) moves.add("S");
        if (maze.isValidMove(x+1,y)) moves.add("D");
        return moves;
    }
}

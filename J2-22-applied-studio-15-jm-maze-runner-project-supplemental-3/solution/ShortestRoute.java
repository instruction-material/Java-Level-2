import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class ShortestRoute {
    private record Cell(int x, int y) {}
    private record Step(Cell previous, String move) {}
    private record Direction(int dx, int dy, String command) {}
    private static final List<Direction> DIRECTIONS = List.of(
        new Direction(0,-1,"W"), new Direction(-1,0,"A"), new Direction(0,1,"S"), new Direction(1,0,"D")
    );

    static List<String> find(Maze maze) {
        var start = new Cell(maze.getPlayerStartX(), maze.getPlayerStartY());
        var pending = new ArrayDeque<Cell>();
        var visited = new HashMap<Cell, Step>();
        pending.add(start);
        visited.put(start, null);
        while (!pending.isEmpty()) {
            Cell current = pending.remove();
            if (maze.isExit(current.x(),current.y())) return trace(visited, current);
            for (var direction : DIRECTIONS) {
                var next = new Cell(current.x() + direction.dx(), current.y() + direction.dy());
                if (maze.isValidMove(next.x(),next.y()) && !visited.containsKey(next)) {
                    visited.put(next, new Step(current,direction.command()));
                    pending.add(next);
                }
            }
        }
        return List.of();
    }

    private static List<String> trace(Map<Cell, Step> visited, Cell end) {
        var result = new ArrayList<String>();
        for (Step step = visited.get(end); step != null; step = visited.get(step.previous())) {
            result.add(step.move());
        }
        Collections.reverse(result);
        return List.copyOf(result);
    }
}

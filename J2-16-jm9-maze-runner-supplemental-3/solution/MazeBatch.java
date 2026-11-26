import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

final class MazeBatch {
    static Map<String, String> inspect(Map<String, Path> inputs) {
        var reports = new TreeMap<String, String>();
        for (var entry : inputs.entrySet()) {
            String result;
            try {
                Maze maze = new Maze(entry.getValue().toString());
                result = "Ready at (" + maze.getPlayerStartX() + ", " + maze.getPlayerStartY() + ")";
            } catch (IllegalArgumentException invalid) {
                result = invalid.getMessage();
            }
            reports.put(entry.getKey(), result);
        }
        return reports;
    }
}

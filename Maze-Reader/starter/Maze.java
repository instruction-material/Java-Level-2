import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class Maze {
    private final char[][] grid;
    private final int playerStartX;
    private final int playerStartY;

    public Maze(String filePath) {
        List<String> rows;
        try {
            rows = Files.readAllLines(Path.of(filePath), StandardCharsets.UTF_8);
        } catch (IOException failure) {
            throw new IllegalArgumentException("Cannot read maze file.", failure);
        }
        if (rows.isEmpty() || rows.get(0).isEmpty()) {
            throw new IllegalArgumentException("Maze must contain at least one cell.");
        }

        int width = rows.get(0).length();
        grid = new char[rows.size()][width];
        int startX = -1;
        int startY = -1;
        int exits = 0;
        for (int y = 0; y < rows.size(); y++) {
            String row = rows.get(y);
            if (row.length() != width) {
                throw new IllegalArgumentException("Maze rows must have the same width.");
            }
            for (int x = 0; x < width; x++) {
                char symbol = row.charAt(x);
                switch (symbol) {
                    case 'P' -> {
                        if (startX >= 0) throw new IllegalArgumentException("Use one player start.");
                        startX = x;
                        startY = y;
                        grid[y][x] = ' ';
                    }
                    case 'E' -> {
                        exits++;
                        grid[y][x] = symbol;
                    }
                    case '#', ' ' -> grid[y][x] = symbol;
                    default -> throw new IllegalArgumentException("Use only walls, spaces, P, and E.");
                }
            }
        }
        // TODO: Reject a board whose exit count differs from one.
        if (startX < 0) {
            throw new IllegalArgumentException("Maze needs one player start and one exit.");
        }
        playerStartX = startX;
        playerStartY = startY;
    }

    public void print() {
        for (int y = 0; y < grid.length; y++) {
            var row = new StringBuilder(grid[y].length);
            for (int x = 0; x < grid[y].length; x++) {
                row.append(x == playerStartX && y == playerStartY ? 'P' : grid[y][x]);
            }
            System.out.println(row);
        }
    }

    public boolean isValidMove(int x, int y) {
        // TODO: Check bounds and inspect the target cell.
        return false;
    }

    public boolean isExit(int x, int y) {
        return isValidMove(x, y) && grid[y][x] == 'E';
    }

    public int getPlayerStartX() {
        return playerStartX;
    }

    public int getPlayerStartY() {
        return playerStartY;
    }
}

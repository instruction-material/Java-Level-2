import java.nio.file.Files;

class ContractTests {
    public static void main(String[] args) throws Exception {
        var maze = new Maze("practice.txt");
        check(maze.getPlayerStartX() == 1 && maze.getPlayerStartY() == 1, "zero-based start");
        check(maze.isValidMove(2, 1), "open space is passable");
        check(!maze.isValidMove(1, 0) && !maze.isValidMove(-1, 1) && !maze.isValidMove(5, 1), "wall and out-of-bounds checks");
        check(maze.isExit(3, 1) && !maze.isExit(2, 1) && !maze.isExit(5, 0), "only valid exit");
        var fixture = Files.createTempFile("maze-reader-test-", ".txt");
        try {
            for (String invalid : new String[] {"", "###\n#P#\n###", "#####\n#PE#\n#####", "#####\n#P?E#\n#####",
                "#####\n#P  #\n#EE##", "#####\n# E #\n#####", "#####\n#PPE#\n#####"}) {
                Files.writeString(fixture, invalid);
                try {
                    new Maze(fixture.toString());
                    throw new AssertionError("accepted malformed board");
                } catch (IllegalArgumentException expected) {
                    check(!expected.getMessage().isBlank(), "specific board error");
                }
            }
        } finally {
            Files.deleteIfExists(fixture);
        }
        System.out.println("Maze reader checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

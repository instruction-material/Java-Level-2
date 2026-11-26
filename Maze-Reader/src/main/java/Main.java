public final class Main {
    public static void main(String[] args) {
        String board = args.length == 0 ? "medium.txt" : args[0];
        try {
            var maze = new Maze(board);
            maze.print();
            System.out.println("Start: (" + maze.getPlayerStartX() + ", " + maze.getPlayerStartY() + ")");
        } catch (IllegalArgumentException invalidMaze) {
            System.out.println(invalidMaze.getMessage());
        }
    }
}

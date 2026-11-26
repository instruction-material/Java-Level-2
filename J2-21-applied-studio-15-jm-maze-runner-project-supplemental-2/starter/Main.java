class Main {
    public static void main(String[] args) {
        var maze = new Maze("practice.txt");
        System.out.println("From start: " + MoveHints.options(maze, maze.getPlayerStartX(), maze.getPlayerStartY()));
        System.out.println("From middle: " + MoveHints.options(maze, 2, 1));
    }
}

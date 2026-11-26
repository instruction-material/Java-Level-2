public final class Main {
    public static void main(String[] args) {
        String path = args.length == 0 ? "hard.txt" : args[0];
        MazeGame game;
        try {
            game = new MazeGame(path);
        } catch (IllegalArgumentException invalidMaze) {
            System.out.println(invalidMaze.getMessage());
            return;
        }
        game.runGame();
    }
}

import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        var maze = new Maze("practice.txt");
        check(MoveHints.options(maze,1,1).equals(List.of("D")), "one legal start direction");
        check(MoveHints.options(maze,2,1).equals(List.of("A","D")), "ordered middle directions");
        check(MoveHints.options(maze,-1,1).isEmpty(), "outside origin");
        check(MoveHints.options(maze,0,1).isEmpty(), "wall origin");
        check(maze.getPlayerStartX() == 1 && maze.getPlayerStartY() == 1, "hint leaves start in place");
        System.out.println("Maze hint checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

import java.util.List;

class ContractTests {
    public static void main(String[] args) {
        var maze = new Maze("practice.txt");
        check(RouteReplay.run(maze, List.of("W", "D", " d ", "W")).equals(new RouteReplay.Report(2, 1, true)), "replay stops at the exit");
        check(RouteReplay.run(maze, List.of("D", "unknown")).equals(new RouteReplay.Report(1, 1, false)), "incomplete replay");
        check(RouteReplay.run(maze, List.of()).equals(new RouteReplay.Report(0, 0, false)), "empty replay");
        check(RouteReplay.run(maze, List.of("D", "D")).equals(new RouteReplay.Report(2, 0, true)), "previous runs leave layout unchanged");
        System.out.println("Maze replay checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

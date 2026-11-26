import java.nio.file.Files;
import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        check(ShortestRoute.find(new Maze("practice.txt")).equals(List.of("D","D")), "two-move shortest route");
        check(ShortestRoute.find(new Maze("blocked.txt")).isEmpty(), "no possible route");
        var layout = Files.createTempFile("shortest-route-", ".txt");
        try {
            Files.writeString(layout, "#####\n#P#E#\n#   #\n#####\n");
            check(ShortestRoute.find(new Maze(layout.toString())).equals(List.of("S","D","D","W")), "reverse predecessor chain with turns");
        } finally {
            Files.deleteIfExists(layout);
        }
        System.out.println("Shortest route checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

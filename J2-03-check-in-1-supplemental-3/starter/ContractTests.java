import java.util.ArrayList;
import java.util.List;

public class ContractTests {
    public static void main(String[] args) {
        Attempt north = new Attempt("North", 80, 15);
        Attempt east = new Attempt("East", 90, 20);
        Attempt west = new Attempt("West", 90, 17);
        Attempt south = new Attempt("South", 80, 15);
        Attempt center = new Attempt("Center", 85, 15);
        List<Attempt> input = new ArrayList<>(List.of(north, east, west, south, center));
        List<Attempt> original = List.copyOf(input);
        check(Main.byPoints(input).equals(List.of(west, east, center, north, south)),
            "points view uses descending points then ascending time");
        check(Main.byTime(input).equals(List.of(center, north, south, west, east)),
            "time view uses ascending time then descending points");
        check(input.equals(original), "both views leave the original list in order");
        check(Main.byPoints(List.of()).isEmpty() && Main.byTime(List.of(north)).equals(List.of(north)),
            "empty and singleton lists stay valid");
        check(Main.byTime(input).equals(Main.byTime(input)), "repeated views agree");
        System.out.println("Ranking practice checks passed");
    }

    private static void check(boolean result, String description) {
        if (!result) throw new AssertionError(description);
    }
}

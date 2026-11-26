import java.util.List;

public class ContractTests {
    public static void main(String[] args) {
        check(Main.scoreOf(2) == 1 && Main.scoreOf(5) == 10, "even and odd scoring rules");
        check(Main.scoreOf(-3) == -6 && Main.scoreOf(-4) == -2, "negative values use the same rules");
        check(Main.scoreOf(Integer.MAX_VALUE) == 4294967294L, "widen before doubling");
        check(Main.computeScore(List.of()) == 0, "empty input scores zero");
        check(Main.computeScore(List.of(2, 5, 8, 13)) == 41, "sample adds each contribution");
        check(Main.computeScore(List.of(2, 5, 8, 13)) == 41, "repeat calls do not share an accumulator");
        check(Main.computeScore(List.of(0, -3, 8, Integer.MAX_VALUE)) == 4294967292L,
            "combine zero, negative values, and large scores");
        System.out.println("Static scoring checks passed");
    }

    private static void check(boolean result, String description) {
        if (!result) throw new AssertionError(description);
    }
}

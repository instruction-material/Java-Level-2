import java.util.Arrays;

public class ContractTests {
    public static void main(String[] args) {
        Player lower = new Player("Casey", 9, 7, 5);
        Player middle = new Player("Riley", 12, 6, 6);
        Player upper = new Player("Jordan", 15, 5, 7);
        check(lower.combinedTotal() == 16 && upper.combinedTotal() == 20, "combined points and rebounds");
        check(!lower.isDisqualified() && middle.isDisqualified() && upper.isDisqualified(),
            "six or more fouls disqualify a player");
        Player extreme = new Player("Large totals", Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        check(extreme.combinedTotal() == 4294967294L, "widen before adding large values");
        check(extreme.compareTo(upper) > 0 && upper.compareTo(extreme) < 0, "compare large totals by sign");
        Player tied = new Player("Tie", 10, 6, 0);
        check(lower.compareTo(tied) == 0, "combined scores tie even if component scores differ");
        Player[] team = {upper, tied, lower, middle};
        Arrays.sort(team);
        check(Arrays.equals(team, new Player[]{tied, lower, middle, upper}),
            "sort by score total while preserving ties");
        System.out.println("Basketball checks passed");
    }

    private static void check(boolean result, String description) {
        if (!result) throw new AssertionError(description);
    }
}

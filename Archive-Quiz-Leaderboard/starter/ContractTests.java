import java.util.ArrayList;
import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        var kim = new Leaderboard.Entry("Kim", 2);
        var lee = new Leaderboard.Entry("Lee", 3);
        var ari = new Leaderboard.Entry("Ari", 3);
        var entries = new ArrayList<>(List.of(kim, lee, ari));
        check(Leaderboard.top(entries, 2).equals(List.of(lee, ari)), "sample");
        check(entries.equals(List.of(kim, lee, ari)), "arrival list unchanged");
        check(Leaderboard.top(entries, 0).isEmpty(), "zero limit");
        check(Leaderboard.top(entries, 6).size() == 3, "limit exceeds list size");
        System.out.println("Checks passed.");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

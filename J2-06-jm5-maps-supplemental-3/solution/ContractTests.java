import java.util.Map;

public class ContractTests {
    public static void main(String[] args) {
        Stock stock = new Stock(Map.of("pen", 5, "folder", 3));
        check(stock.reserve(Map.of("pen", 2, "folder", 1)), "valid mixed request succeeds");
        check(stock.inventory().equals("{folder=2, pen=3}"), "both counts decrease");
        check(!stock.reserve(Map.of("pen", 1, "folder", 3)), "reject an unavailable quantity");
        check(!stock.reserve(Map.of("pen", 1, "tape", 1)), "reject a missing item");
        check(!stock.reserve(Map.of("pen", 0)) && !stock.reserve(Map.of("pen", -1)),
            "reject nonpositive quantities");
        check(stock.reserve(Map.of()) && stock.inventory().equals("{folder=2, pen=3}"),
            "empty request and rejected requests preserve all counts");
        check(stock.reserve(Map.of("pen", 3)) && stock.inventory().equals("{folder=2, pen=0}"),
            "reserve the exact remaining quantity");
        System.out.println("Stock checks passed");
    }
    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }
}

public class ContractTests {
    public static void main(String[] args) {
        Prices prices = new Prices();
        prices.change("tea", 200);
        check(prices.priceFor("tea", -1) == 200, "present key returns its value");
        check(prices.priceFor("unknown", -1) == -1 && prices.size() == 1,
            "missing default does not insert a key");
        check(!prices.addNew("tea", 300) && prices.priceFor("tea", -1) == 200,
            "conditional insertion retains the existing value");
        check(prices.addNew("apple", 0), "zero price is a real stored value");
        check(prices.priceFor("apple", -1) == 0, "stored zero differs from a missing key");
        prices.change("tea", 225);
        check(prices.size() == 2 && prices.remove("tea") == 225, "replacement preserves size");
        check(prices.remove("tea") == null && prices.inventory().equals("{apple=0}"),
            "remove reports a missing key without a side effect");
        System.out.println("Map operations checks passed");
    }
    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }
}

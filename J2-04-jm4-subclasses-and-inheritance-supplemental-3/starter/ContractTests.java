public class ContractTests {
    public static void main(String[] args) {
        Address market = new Address("Market Lane");
        Delivery standard = new Delivery(market);
        Delivery priority = new PriorityDelivery(market);
        check(standard.feeCents() == 500, "standard fee stays at 500");
        check(priority.feeCents() == 800, "priority adds the surcharge through the parent reference");
        check(priority.receipt().equals("Market Lane: 800 cents"), "receipt uses both address and subtype fee");
        check(new PriorityDelivery(new Address("Hill Street")).receipt().equals("Hill Street: 800 cents"),
            "changing the destination leaves the fee rule unchanged");
        check(standard.receipt().equals("Market Lane: 500 cents"), "shared address leaves each fee unchanged");
        System.out.println("Delivery checks passed");
    }
    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }
}

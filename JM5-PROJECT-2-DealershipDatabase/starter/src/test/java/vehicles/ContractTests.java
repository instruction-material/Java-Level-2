package vehicles;

import java.util.List;

public class ContractTests {
    public static void main(String[] args) {
        Dealership dealer = new Dealership();
        Car camry = new Car("Camry", 2019, 13.5, 4);
        Car civic = new Car("Civic", 2021, 30.5, 4);
        check(dealer.inventory().isEmpty() && dealer.find("missing") == null, "empty inventory and missing lookup");
        check(dealer.add("DEMO-02", civic) && dealer.add("DEMO-01", camry), "new keys are inserted");
        check(!dealer.add(" demo-01 ", civic) && dealer.find("demo-01") == camry,
            "normalized duplicate leaves the original entry");
        check(dealer.inventory().equals(List.of(
            "DEMO-01: 2019 Camry, wheels 4, miles 13.5, doors 4",
            "DEMO-02: 2021 Civic, wheels 4, miles 30.5, doors 4")), "inventory is sorted by key");
        List<String> snapshot = dealer.inventory();
        check(dealer.remove(" demo-01 ") == camry && dealer.remove("demo-01") == null,
            "remove returns the old value once");
        check(dealer.inventory().size() == 1 && snapshot.size() == 2, "the snapshot is independent of later removals");
        try {
            dealer.add("  ", carWithDifferentDetails());
            throw new AssertionError("blank VIN was accepted");
        } catch (IllegalArgumentException expected) {
            check(dealer.inventory().size() == 1, "invalid keys leave inventory unchanged");
        }
        System.out.println("Dealership checks passed");
    }

    private static Car carWithDifferentDetails() {
        return new Car("Other", 2020, 0, 2);
    }

    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }
}

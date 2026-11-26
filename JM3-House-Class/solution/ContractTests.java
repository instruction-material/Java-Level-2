import java.util.Arrays;

public class ContractTests {
    public static void main(String[] args) {
        House smaller = new House("Cedar Lane", 500000, 1200);
        House larger = new House("Oak Lane", 500000, 1800);
        House cheaper = new House("Elm Lane", 250000, 2000);
        House tied = new House("Maple Lane", 500000, 1200);
        check(cheaper.compareTo(smaller) < 0, "price is the primary key");
        check(smaller.compareTo(larger) < 0 && larger.compareTo(smaller) > 0, "area breaks a price tie");
        check(cheaper.compareTo(larger) < 0, "the order is transitive");
        check(smaller.compareTo(tied) == 0 && !smaller.equals(tied), "a sorting tie preserves distinct listings");
        House[] listings = {larger, tied, smaller, cheaper};
        Arrays.sort(listings);
        check(Arrays.equals(listings, new House[]{cheaper, tied, smaller, larger}),
            "sort by both keys and retain input order within a tie");
        House zeroPrice = new House("Zero price", 0, 1);
        House maxPrice = new House("High price", Integer.MAX_VALUE, Integer.MAX_VALUE);
        check(zeroPrice.compareTo(maxPrice) < 0, "compare extreme valid prices");
        rejects(() -> new House("Invalid price", -1, 100));
        rejects(() -> new House("Invalid area", 100, 0));
        check(House.getNumHouses() == 6, "count successful constructions, not sorting or rejections");
        System.out.println("House checks passed");
    }

    private static void check(boolean result, String description) {
        if (!result) throw new AssertionError(description);
    }

    private static void rejects(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError("invalid house was accepted");
    }
}

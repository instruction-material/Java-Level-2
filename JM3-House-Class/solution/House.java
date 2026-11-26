import java.util.Objects;

public final class House implements Comparable<House> {
    private static int numHouses;

    private final String address;
    private final int price;
    private final int squareFeet;

    public House(String address, int price, int squareFeet) {
        if (price < 0 || squareFeet <= 0) {
            throw new IllegalArgumentException("Price must be nonnegative and area must be positive");
        }
        this.address = Objects.requireNonNull(address);
        this.price = price;
        this.squareFeet = squareFeet;
        numHouses++;
    }

    public static int getNumHouses() {
        return numHouses;
    }

    @Override
    public int compareTo(House other) {
        int priceOrder = Integer.compare(price, other.price);
        return priceOrder == 0 ? Integer.compare(squareFeet, other.squareFeet) : priceOrder;
    }

    @Override
    public String toString() {
        return address + " | $" + price + " | " + squareFeet + " sq ft";
    }
}

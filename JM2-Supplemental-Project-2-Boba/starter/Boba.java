import java.util.Objects;

public final class Boba {
    private final String flavor;
    private final boolean iced;
    private String topping;

    public Boba(String flavor, boolean iced, String topping) {
        this.flavor = Objects.requireNonNull(flavor);
        this.iced = iced;
        this.topping = Objects.requireNonNull(topping);
    }

    public Boba(String flavor, boolean iced) {
        this(flavor, iced, "undecided"); // TODO: Choose the documented default.
    }

    public String getTopping() {
        return topping;
    }

    public void addTopping(String topping) {
        // TODO: Store the new topping on this order.
    }

    public boolean isSameOrder(Boba other) {
        // TODO: Compare all order fields, including the absent-topping case.
        return false;
    }

    @Override
    public String toString() {
        String description = topping.isEmpty() ? "none" : topping;
        return "Flavor: " + flavor + ", Iced: " + iced + ", Topping: " + description;
    }
}

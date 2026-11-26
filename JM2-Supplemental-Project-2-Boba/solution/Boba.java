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
        this(flavor, iced, "");
    }

    public String getTopping() {
        return topping;
    }

    public void addTopping(String topping) {
        this.topping = Objects.requireNonNull(topping);
    }

    public boolean isSameOrder(Boba other) {
        return other != null && flavor.equals(other.flavor)
            && iced == other.iced && topping.equals(other.topping);
    }

    @Override
    public String toString() {
        String description = topping.isEmpty() ? "none" : topping;
        return "Flavor: " + flavor + ", Iced: " + iced + ", Topping: " + description;
    }
}

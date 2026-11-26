public class Delivery {
    private final Address address;

    public Delivery(Address address) {
        this.address = address;
    }

    public int feeCents() {
        return 500;
    }

    public String receipt() {
        return address.street() + ": " + feeCents() + " cents";
    }
}

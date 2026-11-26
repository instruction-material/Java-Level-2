public final class PriorityDelivery extends Delivery {
    private static final int SURCHARGE_CENTS = 300;

    public PriorityDelivery(Address destination) {
        super(destination);
    }

    @Override
    public int feeCents() {
        return super.feeCents(); // TODO: Add the surcharge.
    }
}

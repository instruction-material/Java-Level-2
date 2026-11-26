package vehicles;

public final class Motorcycle extends LandVehicle {
    private final boolean sidecar;

    public Motorcycle(String model, int year, double miles, boolean sidecar) {
        super(model, year, 2, miles);
        this.sidecar = sidecar;
    }

    @Override
    public String details() {
        return super.details() + ", sidecar " + sidecar;
    }
}

package vehicles;

public class LandVehicle extends Vehicle {
    private final int wheels;
    private double miles;

    public LandVehicle(String model, int year, int wheels, double miles) {
        super(model, year);
        if (wheels < 1) throw new IllegalArgumentException("Wheels must be positive");
        this.wheels = wheels;
        this.miles = checkedMiles(miles);
    }

    public double getMiles() {
        return miles;
    }

    public void addMiles(double distance) {
        miles = checkedMiles(miles + checkedMiles(distance));
    }

    private static double checkedMiles(double miles) {
        if (miles < 0 || !Double.isFinite(miles)) {
            throw new IllegalArgumentException("Mileage must be finite and nonnegative");
        }
        return miles;
    }

    @Override
    public String details() {
        return super.details() + ", wheels " + wheels + ", miles " + miles;
    }
}

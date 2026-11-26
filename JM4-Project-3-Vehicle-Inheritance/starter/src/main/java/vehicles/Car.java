package vehicles;

public final class Car extends LandVehicle {
    private final int doors;

    public Car(String model, int year, double miles, int doors) {
        super(model, year, 4, miles);
        this.doors = doors;
    }

    @Override
    public String details() {
        return super.details(); // TODO: Include the car doors.
    }
}

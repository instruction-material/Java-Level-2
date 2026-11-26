package vehicles;

public class Vehicle {
    private final String model;
    private final int year;

    public Vehicle(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public String details() {
        return year + " " + model;
    }
}

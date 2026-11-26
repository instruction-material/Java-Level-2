public final class Car implements Comparable<Car> {
    private static int numCars;

    private final String type;
    private final String color;
    private int mileage;

    public Car(String type, String color, int mileage) {
        requireNonnegative(mileage);
        this.type = type;
        this.color = color;
        this.mileage = mileage;
        // TODO: Count each successful construction once.
    }

    public Car(String type, String color) {
        this(type, color, 0);
    }

    public static int getNumCars() {
        return numCars;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public int getMileage() {
        return mileage;
    }

    public void drive(int miles) {
        requireNonnegative(miles);
        mileage = Math.addExact(mileage, miles);
    }

    public void driveAll(int... distances) {
        // Optional: Assign mileage only if the entire batch is acceptable.
    }

    private static void requireNonnegative(int miles) {
        if (miles < 0) throw new IllegalArgumentException("Mileage must be nonnegative");
    }

    public boolean sameMileage(Car other) {
        // TODO: Compare mileage when the other car exists.
        return false;
    }

    @Override
    public int compareTo(Car other) {
        // TODO: Order cars by their mileage.
        return 0;
    }

    @Override
    public String toString() {
        return "A " + color + " " + type + " that has driven " + mileage + " miles.";
    }
}

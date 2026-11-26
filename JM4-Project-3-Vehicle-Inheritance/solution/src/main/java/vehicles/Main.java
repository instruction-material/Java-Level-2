package vehicles;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Camry", 2019, 12.5, 4);
        Motorcycle motorcycle = new Motorcycle("Sportster", 2020, 4, false);
        car.addMiles(1);
        for (Vehicle item : new Vehicle[]{car, motorcycle}) {
            System.out.println(item.details());
        }
    }
}

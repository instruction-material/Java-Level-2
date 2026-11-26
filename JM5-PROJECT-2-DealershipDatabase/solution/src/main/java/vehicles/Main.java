package vehicles;

public class Main {
    public static void main(String[] args) {
        Dealership dealer = new Dealership();
        dealer.add("DEMO-02", new Car("Civic", 2021, 30.5, 4));
        Car camry = new Car("Camry", 2019, 13.5, 4);
        dealer.add("DEMO-01", camry);
        System.out.println("Duplicate accepted: " + dealer.add(" demo-01 ", camry));
        System.out.println("Missing VIN: " + dealer.find("DEMO-99"));
        for (String line : dealer.inventory()) {
            System.out.println(line);
        }
    }
}

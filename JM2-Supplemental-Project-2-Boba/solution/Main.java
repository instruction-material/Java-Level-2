import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter flavor, iced (Y or N), and topping (blank for none), one per line.");
            String flavor = readLine(in);
            String iced = readLine(in);
            String topping = readLine(in);
            if (flavor == null || iced == null || topping == null) {
                System.out.println("Order incomplete.");
                return;
            }
            if (flavor.isEmpty() || (!iced.equalsIgnoreCase("Y") && !iced.equalsIgnoreCase("N"))) {
                System.out.println("Use a flavor and Y or N for iced.");
                return;
            }
            Boba order = new Boba(flavor, iced.equalsIgnoreCase("Y"));
            if (!topping.isEmpty()) {
                order.addTopping(topping);
            }
            System.out.println(order);
            Boba popular = new Boba("brown sugar milk tea", true, "pearls");
            if (order.isSameOrder(popular)) {
                System.out.println("Matches the most popular order.");
            }
        }
    }

    private static String readLine(Scanner in) {
        return in.hasNextLine() ? in.nextLine().strip() : null;
    }
}

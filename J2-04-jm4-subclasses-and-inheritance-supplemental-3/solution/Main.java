public class Main {
    public static void main(String[] args) {
        Address address = new Address("Market Lane");
        for (Delivery delivery : new Delivery[]{new Delivery(address), new PriorityDelivery(address)}) {
            System.out.println(delivery.receipt());
        }
    }
}

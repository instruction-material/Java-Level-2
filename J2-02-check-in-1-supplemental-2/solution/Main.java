public class Main {
    public static void main(String[] args) {
        Ticket first = new Ticket();
        Ticket second = new Ticket();
        System.out.println("Ticket numbers: " + first.getNumber() + ", " + second.getNumber());
        System.out.println("First redemption: " + first.redeem());
        System.out.println("Repeated redemption: " + first.redeem());
        System.out.println("Second ticket redeemed: " + second.isRedeemed());
        System.out.println("Issued: " + Ticket.getIssuedCount() + ", redeemed: " + Ticket.getRedeemedCount());
    }
}

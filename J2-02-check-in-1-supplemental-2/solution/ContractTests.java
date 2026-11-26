public class ContractTests {
    public static void main(String[] args) {
        check(Ticket.getIssuedCount() == 0 && Ticket.getRedeemedCount() == 0, "fresh program starts at zero");
        Ticket first = new Ticket();
        Ticket second = new Ticket();
        check(first.getNumber() == 1 && second.getNumber() == 2, "numbers follow issuance order");
        check(!first.isRedeemed() && !second.isRedeemed(), "new tickets can be used");
        check(first.redeem(), "first use succeeds");
        check(first.isRedeemed() && !second.isRedeemed(), "only the receiver changes");
        check(!first.redeem() && Ticket.getRedeemedCount() == 1, "retry does not add another redemption");
        check(second.redeem() && Ticket.getRedeemedCount() == 2, "the other ticket remains eligible");
        check(Ticket.getIssuedCount() == 2, "redemption does not issue tickets");
        System.out.println("Ticket practice checks passed");
    }

    private static void check(boolean result, String description) {
        if (!result) throw new AssertionError(description);
    }
}

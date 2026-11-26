public final class Ticket {
    private static int issuedCount;
    private static int redeemedCount;

    private final int number;
    private boolean redeemed;

    public Ticket() {
        issuedCount++;
        number = issuedCount;
    }

    public int getNumber() {
        return number;
    }

    public boolean isRedeemed() {
        return redeemed;
    }

    public static int getIssuedCount() {
        return issuedCount;
    }

    public static int getRedeemedCount() {
        return redeemedCount;
    }

    public boolean redeem() {
        if (redeemed) {
            return false;
        }
        redeemed = true;
        redeemedCount++;
        return true;
    }
}

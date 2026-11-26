class ContractTests {
    public static void main(String[] args) {
        var a = new BankAccount(100);
        var b = new BankAccount(50);
        check(Transfers.move(a, b, 30), "move valid cents");
        check(a.balanceCents() == 70 && b.balanceCents() == 80, "conserve total");
        check(!Transfers.move(a, b, 71) && !Transfers.move(a, b, 0), "reject amount bounds");
        check(!Transfers.move(a, a, 1) && !Transfers.move(null, b, 1), "reject unusable accounts");
        var full = new BankAccount(Long.MAX_VALUE);
        check(!Transfers.move(a, full, 1), "reject destination overflow");
        check(a.balanceCents() == 70 && b.balanceCents() == 80 && full.balanceCents() == Long.MAX_VALUE, "unchanged balances");
        System.out.println("Transfer checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

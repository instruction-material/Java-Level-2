class ContractTests {
    public static void main(String[] args) {
        var bank = new BankSystem();
        check(bank.open("DEMO-A", 200) && bank.open("DEMO-B", 50), "create accounts");
        var first = bank.account("DEMO-A");
        check(first.deposit(75) && first.balanceCents() == 275, "add exact cents");
        check(!first.withdraw(500) && !first.withdraw(-1) && !first.deposit(0), "reject invalid amounts");
        check(first.balanceCents() == 275, "failed changes leave balance in place");
        check(first.withdraw(25) && first.balanceCents() == 250, "valid withdrawal");
        check(!bank.open("DEMO-A", 3) && first == bank.account("DEMO-A"), "retain duplicate original");
        check(bank.account("DEMO-X") == null && !bank.open("A", 1), "missing and invalid IDs");
        check(bank.account("DEMO-B").balanceCents() == 50, "independent balances");
        var full = new BankAccount(Long.MAX_VALUE);
        check(!full.deposit(1) && full.balanceCents() == Long.MAX_VALUE, "reject overflow");
        System.out.println("Fictional bank checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

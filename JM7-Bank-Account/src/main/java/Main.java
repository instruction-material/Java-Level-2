class Main {
    public static void main(String[] args) {
        var bank = new BankSystem();
        bank.open("DEMO-A", 200);
        bank.open("DEMO-B", 50);
        var first = bank.account("DEMO-A");
        System.out.println("Deposit accepted: " + first.deposit(75));
        System.out.println("Overdraft accepted: " + first.withdraw(500));
        System.out.println("Withdrawal accepted: " + first.withdraw(25));
        bank.snapshot().forEach((id, cents) -> System.out.println(id + ": " + cents + " cents"));
    }
}

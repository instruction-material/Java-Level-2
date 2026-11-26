class Main {
    public static void main(String[] args) {
        var bank = new BankSystem();
        bank.open("DEMO-A", 100);
        bank.open("DEMO-B", 50);
        System.out.println("Moved: " + Transfers.move(bank.account("DEMO-A"), bank.account("DEMO-B"), 30));
        System.out.println("Overdraw accepted: " + Transfers.move(bank.account("DEMO-A"), bank.account("DEMO-B"), 90));
        System.out.println("Balances: " + bank.snapshot());
    }
}

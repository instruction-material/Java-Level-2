class Main {
    public static void main(String[] args) throws Exception {
        try (var folder = new WorkbookFolder()) {
            var file = folder.file("accounts.txt");
            var before = new BankSystem();
            before.open("DEMO-A", 250);
            before.open("DEMO-B", 50);
            BalanceFile.save(file, before);
            before.account("DEMO-A").deposit(25);
            BalanceFile.save(file, before);
            System.out.println("Reopened: " + BalanceFile.load(file).snapshot());
        }
    }
}

import java.nio.file.Files;
import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        try (var folder = new WorkbookFolder()) {
            var path = folder.file("accounts.txt");
            var bank = new BankSystem();
            bank.open("DEMO-B", 50);
            bank.open("DEMO-A", 250);
            BalanceFile.save(path, bank);
            var reopened = BalanceFile.load(path);
            check(reopened.snapshot().equals(bank.snapshot()), "reopen exact balances");
            reopened.account("DEMO-A").deposit(5);
            BalanceFile.save(path, reopened);
            check(Files.readAllLines(path).equals(List.of("BALANCES-1", "DEMO-A,255", "DEMO-B,50")), "replace complete snapshot in sorted order");
            check(BalanceFile.load(folder.file("none.txt")).snapshot().isEmpty(), "missing file starts empty");
            var diagnostic = folder.file("bad.txt");
            for (String bad : List.of("DEMO-A,2", "DEMO-B,-1", "DEMO-C,invalid", "unexpected")) {
                Files.writeString(diagnostic, "BALANCES-1\nDEMO-A,10\n" + bad + "\n");
                try {
                    BalanceFile.load(diagnostic);
                    throw new AssertionError("invalid snapshot accepted");
                } catch (java.io.IOException expected) {
                    check(expected.getMessage().equals("Invalid account on line 3."), "specific snapshot failure");
                }
            }
            check(reopened.account("DEMO-A").balanceCents() == 255, "no partial load");
            try (var files = Files.list(path.getParent())) {
                check(files.noneMatch(file -> file.toString().endsWith(".tmp")), "temporary staging files cleaned");
            }
        }
        System.out.println("Balance snapshot checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

final class Transfers {
    static boolean move(BankAccount source, BankAccount destination, long cents) {
        if (source == null || destination == null || source == destination || cents > source.balanceCents()
            || !destination.canDeposit(cents)) return false;
        source.withdraw(cents);
        destination.deposit(cents);
        return true;
    }
}

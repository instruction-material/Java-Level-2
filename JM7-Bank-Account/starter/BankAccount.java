public final class BankAccount {
    private long cents;

    BankAccount(long openingCents) {
        if (openingCents < 0) throw new IllegalArgumentException("Opening balance must be nonnegative.");
        cents = openingCents;
    }

    public long balanceCents() {
        return cents;
    }

    boolean canDeposit(long amount) {
        return amount > 0 && amount <= Long.MAX_VALUE - cents;
    }

    public boolean deposit(long amount) {
        // TODO: Update the balance only after validating the amount.
        return false;
    }

    public boolean withdraw(long amount) {
        if (amount <= 0 || amount > cents) return false;
        cents -= amount;
        return true;
    }
}

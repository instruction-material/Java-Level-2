import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public final class BankSystem {
    private final Map<String, BankAccount> accounts = new TreeMap<>();

    public boolean open(String id, long openingCents) {
        if (id == null || !id.matches("DEMO-[A-Z0-9]+") || openingCents < 0) return false;
        return accounts.putIfAbsent(id, new BankAccount(openingCents)) == null;
    }

    public BankAccount account(String id) {
        return id == null ? null : accounts.get(id);
    }

    public Map<String, Long> snapshot() {
        var balances = new TreeMap<String, Long>();
        accounts.forEach((id, account) -> balances.put(id, account.balanceCents()));
        return Collections.unmodifiableMap(balances);
    }
}

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

final class Directory {
    private final Map<String, String> accounts = new HashMap<>();

    void add(String service, String alias) {
        // TODO: Implement the contract described in README.md.
        return;
    }

    Optional<String> find(String service) {
        return Optional.ofNullable(accounts.get(service.strip()));
    }
}

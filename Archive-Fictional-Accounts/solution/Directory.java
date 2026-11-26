import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

final class Directory {
    private final Map<String, String> accounts = new HashMap<>();

    void add(String service, String alias) {
        String key = service.strip();
        String name = alias.strip();
        if (key.isEmpty() || name.isEmpty()) throw new IllegalArgumentException("Use nonblank names.");
        if (accounts.putIfAbsent(key, name) != null) {
            throw new IllegalArgumentException("Service already exists.");
        }
    }

    Optional<String> find(String service) {
        return Optional.ofNullable(accounts.get(service.strip()));
    }
}

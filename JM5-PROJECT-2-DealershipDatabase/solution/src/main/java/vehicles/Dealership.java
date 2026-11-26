package vehicles;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public final class Dealership {
    private final Map<String, Car> byVin = new TreeMap<>();

    private static String key(String vin) {
        String normalized = vin.strip().toUpperCase(Locale.ROOT);
        if (normalized.isEmpty()) throw new IllegalArgumentException("VIN must not be blank");
        return normalized;
    }

    public boolean add(String vin, Car car) {
        return byVin.putIfAbsent(key(vin), Objects.requireNonNull(car)) == null;
    }

    public Car find(String vin) {
        return byVin.get(key(vin));
    }

    public Car remove(String vin) {
        return byVin.remove(key(vin));
    }

    public List<String> inventory() {
        List<String> rows = new ArrayList<>();
        byVin.forEach((vin, car) -> rows.add(vin + ": " + car.details()));
        return List.copyOf(rows);
    }
}

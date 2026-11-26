import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public final class Attendance {
    private final Map<String, Set<String>> rooms = new TreeMap<>();

    public boolean arrive(String room, String name) {
        return rooms.computeIfAbsent(room, unused -> new TreeSet<>()).add(name);
    }

    public int count(String room) {
        return rooms.getOrDefault(room, Set.of()).size();
    }

    public int roomCount() {
        return rooms.size();
    }
}

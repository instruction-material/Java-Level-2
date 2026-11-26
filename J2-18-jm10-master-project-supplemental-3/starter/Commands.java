import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.IntUnaryOperator;

final class Commands {
    private final Map<String, IntUnaryOperator> handlers = new HashMap<>();

    boolean register(String command, IntUnaryOperator handler) {
        String name = key(command);
        return !name.isEmpty() && handlers.putIfAbsent(name, Objects.requireNonNull(handler)) == null;
    }

    int run(String command, int argument) {
        var handler = handlers.get(key(command));
        if (handler == null) throw new IllegalArgumentException("Unknown command.");
        // TODO: Call the selected handler with its argument.
        return argument;
    }

    private static String key(String command) {
        return command.strip().toLowerCase(Locale.ROOT);
    }
}

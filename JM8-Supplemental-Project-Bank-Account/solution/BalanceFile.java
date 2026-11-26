import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

final class BalanceFile {
    private static final String VERSION = "BALANCES-1";

    static void save(Path file, BankSystem bank) throws IOException {
        var rows = new ArrayList<String>();
        rows.add(VERSION);
        bank.snapshot().forEach((id, cents) -> rows.add(id + "," + cents));
        Path destination = file.toAbsolutePath();
        Path staged = Files.createTempFile(destination.getParent(), "balances-", ".tmp");
        try {
            Files.write(staged, rows, StandardCharsets.UTF_8);
            Files.move(staged, destination, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } finally {
            Files.deleteIfExists(staged);
        }
    }

    static BankSystem load(Path file) throws IOException {
        var loaded = new BankSystem();
        try (var reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            if (!VERSION.equals(reader.readLine())) throw new IOException("Unknown balance format on line 1.");
            int line = 1;
            for (String row = reader.readLine(); row != null; row = reader.readLine()) {
                line++;
                String[] fields = row.split(",", -1);
                if (fields.length != 2) throw invalid(line);
                long cents;
                try {
                    cents = Long.parseLong(fields[1]);
                } catch (NumberFormatException malformed) {
                    throw invalid(line);
                }
                if (!loaded.open(fields[0], cents)) throw invalid(line);
            }
        } catch (NoSuchFileException missing) {
            return loaded;
        }
        return loaded;
    }

    private static IOException invalid(int line) {
        return new IOException("Invalid account on line " + line + ".");
    }
}

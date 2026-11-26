import java.util.List;

final class CountedSum {
    static long sum(List<String> lines) {
        if (lines.isEmpty()) throw new IllegalArgumentException("Missing count.");
        int count = Integer.parseInt(lines.getFirst().strip());
        if (count < 0 || count != lines.size() - 1) {
            throw new IllegalArgumentException("Expected one line for each value.");
        }
        long total = 0;
        for (int i = 1; i < lines.size(); i++) {
            total += Integer.parseInt(lines.get(i).strip());
        }
        return total;
    }
}

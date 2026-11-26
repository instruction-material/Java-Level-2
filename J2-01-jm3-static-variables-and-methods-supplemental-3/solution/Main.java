import java.util.List;

public class Main {
    private static final List<Integer> SAMPLE_VALUES = List.of(2, 5, 8, 13);
    private static final int EVEN_DIVISOR = 2;
    private static final int ODD_MULTIPLIER = 2;

    static long scoreOf(int value) {
        return value % EVEN_DIVISOR == 0 ? value / EVEN_DIVISOR : (long) value * ODD_MULTIPLIER;
    }

    static long computeScore(List<Integer> values) {
        long total = 0;
        for (int value : values) {
            total += scoreOf(value);
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(computeScore(SAMPLE_VALUES));
    }
}

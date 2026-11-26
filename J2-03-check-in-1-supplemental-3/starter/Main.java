import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    static List<Attempt> byPoints(List<Attempt> attempts) {
        // TODO: Order a copy by descending points, then ascending seconds.
        return new ArrayList<>(attempts);
    }

    static List<Attempt> byTime(List<Attempt> attempts) {
        // TODO: Order a copy by ascending seconds, then descending points.
        return new ArrayList<>(attempts);
    }

    private static List<Attempt> sortedCopy(List<Attempt> attempts, Comparator<Attempt> rule) {
        List<Attempt> result = new ArrayList<>(attempts);
        result.sort(rule);
        return result;
    }

    public static void main(String[] args) {
        List<Attempt> attempts = List.of(new Attempt("North", 80, 15),
            new Attempt("East", 90, 20), new Attempt("West", 90, 17),
            new Attempt("South", 80, 15));
        System.out.println("Points view: " + byPoints(attempts));
        System.out.println("Time view: " + byTime(attempts));
        System.out.println("Original: " + attempts);
    }
}

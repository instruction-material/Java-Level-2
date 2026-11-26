import java.util.HashMap;
import java.util.Map;

final class Board {
    enum Outcome { PLAYING, X_WINS, O_WINS, TIE }

    private final Map<Integer, Character> marks = new HashMap<>();
    private char next = 'X';
    private Outcome outcome = Outcome.PLAYING;

    boolean play(int row, int column) {
        if (outcome != Outcome.PLAYING || !valid(row) || !valid(column)) return false;
        if (marks.putIfAbsent(row * 3 + column, next) != null) return false;
        if (won()) {
            outcome = next == 'X' ? Outcome.X_WINS : Outcome.O_WINS;
        } else if (marks.size() == 9) {
            outcome = Outcome.TIE;
        } else {
            next = next == 'X' ? 'O' : 'X';
        }
        return true;
    }

    private static boolean valid(int index) {
        return index >= 0 && index < 3;
    }

    private boolean won() {
        for (int index = 0; index < 3; index++) {
            int row = 3 * index;
            if (line(row, row + 1, row + 2) || line(index, index + 3, index + 6)) return true;
        }
        return line(0, 4, 8) || line(2, 4, 6);
    }

    private boolean line(int first, int second, int third) {
        return marks.getOrDefault(first, ' ') == next && marks.getOrDefault(second, ' ') == next
            && marks.getOrDefault(third, ' ') == next;
    }

    String view() {
        var display = new StringBuilder();
        for (int index = 0; index < 9; index++) {
            if (index > 0) display.append(index % 3 == 0 ? " / " : " ");
            display.append(marks.getOrDefault(index, '.'));
        }
        return display.toString();
    }

    char next() {
        return next;
    }

    Outcome outcome() {
        return outcome;
    }
}

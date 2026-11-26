import java.util.ArrayList;
import java.util.List;

class ContractTests {
    public static void main(String[] args) throws Exception {
        var row = new Board();
        for (int[] cells : new int[][] {{0,0},{1,0},{0,1},{1,1},{0,2}}) check(row.play(cells[0],cells[1]), "legal move");
        check(row.outcome() == Board.Outcome.X_WINS, "row wins");
        check(!row.play(2,0), "reject a move after the outcome");
        var column = new Board();
        for (int[] cells : new int[][] {{0,0},{0,1},{2,0},{1,1},{2,2},{2,1}}) check(column.play(cells[0],cells[1]), "legal column sequence");
        check(column.outcome() == Board.Outcome.O_WINS, "column wins");
        var diagonal = new Board();
        for (int[] cells : new int[][] {{0,2},{1,0},{1,1},{0,0}}) check(diagonal.play(cells[0],cells[1]), "legal diagonal setup");
        check(diagonal.play(2,0) && diagonal.outcome() == Board.Outcome.X_WINS, "diagonal wins");
        var tied = new Board();
        for (int[] cells : new int[][] {{0,0},{0,1},{0,2},{1,0},{1,2},{1,1},{2,1},{2,2},{2,0}}) {
            check(tied.play(cells[0],cells[1]), "legal tie sequence");
        }
        check(tied.outcome() == Board.Outcome.TIE, "full board ties");
        var fresh = new Board();
        var input = List.of("bad","1 1","1 1","2 1","Q").iterator();
        var output = new ArrayList<String>();
        GameSession.play(fresh, () -> input.hasNext() ? input.next() : null, output::add);
        check(output.stream().filter(line -> line.startsWith("Choose an open cell")).count() == 2, "recover from malformed and occupied moves");
        check(fresh.view().equals("X . . / O . . / . . .") && fresh.next() == 'X', "rejected moves preserve the board and turn");
        GameSession.play(new Board(), () -> null, output::add);
        check(output.getLast().equals("Round ended."), "scripted EOF exits");
        System.out.println("Capstone board checks passed");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

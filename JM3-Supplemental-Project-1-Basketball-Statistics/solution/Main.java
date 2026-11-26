import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Player[] team = {
            new Player("Riley", 12, 6, 6),
            new Player("Casey", 9, 7, 5),
            new Player("Jordan", 15, 5, 2)
        };
        Arrays.sort(team);
        for (Player player : team) {
            System.out.println(player);
        }
    }
}

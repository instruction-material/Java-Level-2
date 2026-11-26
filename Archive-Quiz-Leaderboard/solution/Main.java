import java.util.List;

class Main {
    public static void main(String[] args) {
        System.out.println("Top: " + Leaderboard.top(List.of(
            new Leaderboard.Entry("Kim", 2),
            new Leaderboard.Entry("Lee", 3),
            new Leaderboard.Entry("Ari", 3)
        ), 2));
    }
}

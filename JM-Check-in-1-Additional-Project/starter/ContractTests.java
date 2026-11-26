import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContractTests {
    public static void main(String[] args) {
        Card club = new Card(13, 1);
        Card heartAce = new Card(1, 3);
        Card heartTwo = new Card(2, 3);
        Card spade = new Card(1, 4);
        check(club.compareTo(heartAce) < 0, "suit sorts before rank");
        check(heartAce.compareTo(heartTwo) < 0 && heartTwo.compareTo(heartAce) > 0,
            "rank breaks a suit tie");
        check(heartAce.compareTo(new Card(1, 3)) == 0, "matching sort keys tie");
        Card[] hand = {heartTwo, club, heartAce, spade};
        Arrays.sort(hand);
        check(Arrays.equals(hand, new Card[]{club, heartAce, heartTwo, spade}), "sorts all four cards");
        check("1 of spades".equals(spade.toString()), "uses the documented suit labels");
        rejects(0, 1);
        rejects(14, 1);
        rejects(1, 0);
        rejects(1, 5);
        Card[] first = Main.draw(7);
        Card[] second = Main.draw(7);
        check(Arrays.toString(first).equals(Arrays.toString(second)), "a fixed seed produces the same hand");
        Set<String> keys = new HashSet<>();
        for (Card card : first) keys.add(card.getRank() + "/" + card.getSuit());
        check(first.length == 5 && keys.size() == 5, "sample hand has no duplicate cards");
        System.out.println("Card checks passed");
    }

    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }

    private static void rejects(int rank, int suit) {
        try {
            new Card(rank, suit);
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError("invalid card accepted");
    }
}

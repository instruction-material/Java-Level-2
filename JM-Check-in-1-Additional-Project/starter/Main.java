import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    static Card[] draw(long seed) {
        List<Card> deck = new ArrayList<>();
        for (int suit = 1; suit <= 4; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                deck.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(deck, new Random(seed));
        return deck.subList(0, 5).toArray(Card[]::new);
    }

    public static void main(String[] args) {
        Card[] hand = args.length == 0
            ? new Card[]{new Card(2, 3), new Card(13, 1), new Card(1, 3), new Card(1, 4)}
            : draw(Long.parseLong(args[0]));
        System.out.println("Unsorted: " + Arrays.toString(hand));
        Arrays.sort(hand);
        System.out.println("Sorted: " + Arrays.toString(hand));
    }
}

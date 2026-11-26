public final class Card implements Comparable<Card> {
    private static final String[] SUITS = {"clubs", "diamonds", "hearts", "spades"};

    private final int rank;
    private final int suit;

    public Card(int rank, int suit) {
        if (rank < 1 || rank > 13 || suit < 1 || suit > 4) {
            throw new IllegalArgumentException("Rank must be 1–13 and suit 1–4");
        }
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card other) {
        // TODO: Compare suits, then ranks within the same suit.
        return 0;
    }

    @Override
    public String toString() {
        return rank + " of " + SUITS[suit - 1];
    }
}

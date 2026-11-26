import java.util.Objects;

public final class Player implements Comparable<Player> {
    private static final int MAX_FOULS = 6;

    private final String name;
    private final int points;
    private final int rebounds;
    private final int fouls;

    public Player(String name, int points, int rebounds, int fouls) {
        if (points < 0 || rebounds < 0 || fouls < 0) {
            throw new IllegalArgumentException("Stats must be nonnegative");
        }
        this.name = Objects.requireNonNull(name);
        this.points = points;
        this.rebounds = rebounds;
        this.fouls = fouls;
    }

    public long combinedTotal() {
        // TODO: Widen before adding the stats.
        return 0;
    }

    public boolean isDisqualified() {
        // TODO: Apply the shared foul limit.
        return false;
    }

    @Override
    public int compareTo(Player other) {
        // TODO: Compare the two combined totals.
        return 0;
    }

    @Override
    public String toString() {
        String status = isDisqualified() ? "disqualified" : "eligible";
        return name + " | total " + combinedTotal() + " | fouls " + fouls + " | " + status;
    }
}

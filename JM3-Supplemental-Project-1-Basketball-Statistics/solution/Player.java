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
        return (long) points + rebounds;
    }

    public boolean isDisqualified() {
        return fouls >= MAX_FOULS;
    }

    @Override
    public int compareTo(Player other) {
        return Long.compare(combinedTotal(), other.combinedTotal());
    }

    @Override
    public String toString() {
        String status = isDisqualified() ? "disqualified" : "eligible";
        return name + " | total " + combinedTotal() + " | fouls " + fouls + " | " + status;
    }
}

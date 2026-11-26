public class Player implements Comparable<Player> {
    private String name;
    private int points;
    private int rebounds;
    private int fouls;
    // set a static variable for the max fouls allowed in a game
    private static final int MAX_FOULS = 6;

    public Player(String n, int p, int r, int f) {
        name = n;
        points = p;
        rebounds = r;
        fouls = f;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getRebounds() {
        return rebounds;
    }

    public int getFouls() {
        return fouls;
    }

    public boolean isDisqualified() {
        return fouls >= MAX_FOULS;
    }

    public String toString() {
        return name + "\nPoints: " + points + "\nRebounds: " + rebounds +
            "\nFouls: " + fouls;
    }

    @Override
    public int compareTo(Player other) {
        long total = (long) points + rebounds;
        long otherTotal = (long) other.points + other.rebounds;
        return Long.compare(total, otherTotal);
    }
}

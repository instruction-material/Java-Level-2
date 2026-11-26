class Main {
    public static void main(String[] args) {
        System.out.println("Shortest route: " + ShortestRoute.find(new Maze("practice.txt")));
        System.out.println("Blocked route: " + ShortestRoute.find(new Maze("blocked.txt")));
    }
}

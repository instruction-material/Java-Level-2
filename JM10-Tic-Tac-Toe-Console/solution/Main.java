import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (var input = new Scanner(System.in)) {
            GameSession.play(new Board(), () -> input.hasNextLine() ? input.nextLine() : null, System.out::println);
        }
    }
}

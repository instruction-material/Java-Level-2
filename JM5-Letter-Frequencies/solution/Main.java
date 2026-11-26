import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            if (!input.hasNextLine()) {
                System.out.println("No input received.");
                return;
            }
            System.out.println(LetterCounts.report(input.nextLine()));
        }
    }
}

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Main {
    static Map<Integer, Integer> countLetters(String sentence) {
        Map<Integer, Integer> frequencies = new TreeMap<>();
        sentence.codePoints()
            .filter(Character::isLetter)
            .map(Character::toLowerCase)
            .forEach(letter -> frequencies.merge(letter, 1, Integer::sum));
        return frequencies;
    }

    private static String textFor(int letter) {
        return new String(Character.toChars(letter));
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter a sentence:");
            if (!scanner.hasNextLine()) {
                System.out.println("No input received.");
                return;
            }

            Map<Integer, Integer> frequencies = countLetters(scanner.nextLine());
            if (frequencies.isEmpty()) {
                System.out.println("No letters found.");
                return;
            }

            Map.Entry<Integer, Integer> mostFrequent = null;
            for (var entry : frequencies.entrySet()) {
                System.out.println(textFor(entry.getKey()) + ": " + entry.getValue());
                if (mostFrequent == null || entry.getValue() > mostFrequent.getValue()) {
                    mostFrequent = entry;
                }
            }
            System.out.println("Most frequent: " + textFor(mostFrequent.getKey())
                + " (" + mostFrequent.getValue() + ")");
        }
    }
}

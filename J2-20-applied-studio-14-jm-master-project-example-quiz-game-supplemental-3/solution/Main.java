import java.util.List;

class Main {
    public static void main(String[] args) {
        var cards = List.of(new PracticeSet.Card("Science","What orbits Earth?"),
            new PracticeSet.Card("History","What is an artifact?"),
            new PracticeSet.Card("Science","What orbits Earth?"),
            new PracticeSet.Card("Science","What is a solid?"));
        System.out.println("Practice: " + PracticeSet.prompts(cards, "Science", 2));
    }
}

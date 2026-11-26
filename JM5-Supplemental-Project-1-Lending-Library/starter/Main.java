import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.add("The Cat in the Hat", "Dr. Seuss");
        library.add("Green Eggs and Ham", "Dr. Seuss");
        library.add("The Hobbit", "J. R. R. Tolkien");
        try (Scanner input = new Scanner(System.in)) {
            if (!input.hasNextLine()) {
                System.out.println("No input received.");
                return;
            }
            switch (input.nextLine().strip()) {
                case "1" -> {
                    if (!input.hasNextLine()) {
                        System.out.println("No title received.");
                        return;
                    }
                    String author = library.findAuthor(input.nextLine());
                    System.out.println(author == null ? "Title not found." : author);
                }
                case "2" -> {
                    if (!input.hasNextLine()) {
                        System.out.println("No author received.");
                        return;
                    }
                    var titles = library.titlesBy(input.nextLine());
                    if (titles.isEmpty()) System.out.println("No matching titles.");
                    else titles.forEach(System.out::println);
                }
                case "3" -> library.inventory().forEach(System.out::println);
                default -> System.out.println("Choose 1, 2, or 3.");
            }
        }
    }
}

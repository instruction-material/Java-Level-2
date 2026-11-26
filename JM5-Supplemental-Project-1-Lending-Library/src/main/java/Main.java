import java.util.*;

class Main {
    public static void main(String[] args) {

        // Create a map and add nine pairs of books and authors
        Map<String, String> books = new TreeMap<>();
        books.put("Green Eggs and Ham", "Dr. Seuss");
        books.put("Stuart Little", "E.B. White");
        books.put("Charlotte's Web", "E.B. White");
        books.put("The Hobbit", "J. R. R. Tolkien");
        books.put("The Cat in the Hat", "Dr. Seuss");
        books.put("Charlie and the Chocolate Factory", "Roald Dahl");
        books.put("Little Women", "Louisa May Alcott");
        books.put("Harry Potter", "J.K. Rowling");
        books.put("The Giving Tree", "Shel Silverstein");

        // Get input from user
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the lending library!");
        System.out.println("Enter 1 to search for a book by title");
        System.out.println("Enter 2 to search for a book by author");
        System.out.println("Enter 3 to print all of our inventory");
        if (!in.hasNextLine()) {
            System.out.println("No input received.");
            return;
        }
        String choice = in.nextLine().strip();

        // Search for book by title
        if (choice.equals("1")) {
            System.out.println(
                "What is the title of the book you are looking for? ");
            if (!in.hasNextLine()) {
                System.out.println("No title received.");
                return;
            }
            String title = in.nextLine().strip();
            if (books.containsKey(title)) {
                System.out.println("We have the book " + title + " by " +
                                   books.get(title));
            } else {
                System.out.println(
                    "Sorry, it looks like we don't have any books called " +
                    title);
            }

            // Search for books by author
        } else if (choice.equals("2")) {
            System.out.println(
                "What is the name of the author you are looking for?");
            if (!in.hasNextLine()) {
                System.out.println("No author received.");
                return;
            }
            String author = in.nextLine().strip();
            Boolean found = false;
            System.out.println("Books by " + author + ": ");
            for (String title : books.keySet()) {
                // We need to use .equals to check string equality
                if (books.get(title).equals(author)) {
                    System.out.println(title);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Sorry, we have 0 books by " + author);
            }

            // Print all books and authors
        } else if (choice.equals("3")) {
            System.out.println("Here's our inventory");
            for (String title : books.keySet()) {
                System.out.println(title + " by " + books.get(title));
            }

        } else {
            System.out.println("Input not valid! Please try again.");
        }
    }
}

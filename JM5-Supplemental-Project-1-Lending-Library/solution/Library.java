import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class Library {
    private final Map<String, String> books = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public void add(String title, String author) {
        books.put(title.strip(), author.strip());
    }

    public String findAuthor(String title) {
        return books.get(title.strip());
    }

    public List<String> titlesBy(String author) {
        List<String> titles = new ArrayList<>();
        for (var entry : books.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(author.strip())) titles.add(entry.getKey());
        }
        return List.copyOf(titles);
    }

    public List<String> inventory() {
        List<String> rows = new ArrayList<>();
        books.forEach((title, author) -> rows.add(title + " by " + author));
        return List.copyOf(rows);
    }
}

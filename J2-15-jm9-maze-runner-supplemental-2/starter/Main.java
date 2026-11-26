import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

class Main {
    public static void main(String[] args) throws Exception {
        var result = RouteReplay.run(new Maze("practice.txt"), Files.readAllLines(Path.of("commands.txt"), StandardCharsets.UTF_8));
        System.out.println("Accepted: " + result.accepted() + ", rejected: " + result.rejected() + ", escaped: " + result.escaped());
    }
}

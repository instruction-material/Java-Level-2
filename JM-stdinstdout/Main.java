import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

class Main {
    public static void main(String[] args) throws IOException {
        var input = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String line = input.readLine();
        if (line != null) {
            System.out.println(line);
        }
    }
}

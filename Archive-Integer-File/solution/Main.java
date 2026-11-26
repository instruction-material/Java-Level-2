import java.nio.file.Path;

class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Values: " + IntegerFile.read(Path.of("values.txt")));
    }
}

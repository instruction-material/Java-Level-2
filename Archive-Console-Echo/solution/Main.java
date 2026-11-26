import java.io.BufferedReader;
import java.io.StringReader;

class Main {
    public static void main(String[] args) throws Exception {
        var input = new BufferedReader(new StringReader("  red fox  \nnext\n"));
        System.out.println("Echo: [" + ConsoleLine.read(input) + "]");
    }
}

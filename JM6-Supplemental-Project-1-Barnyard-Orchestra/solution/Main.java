import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

class Main {
    private static final Map<String, String> SOUNDS = Map.of(
        "cow", "moo", "pig", "oink", "sheep", "baa", "chicken", "baawk",
        "frog", "ribbit", "dog", "bark", "cat", "meow"
    );

    public static void main(String[] args) throws InterruptedException {
        var names = new ArrayList<String>();
        var recordings = new ArrayList<List<String>>();
        var workers = new ArrayList<Thread>();
        try (var input = new Scanner(System.in)) {
            for (int slot = 0; slot < 3; slot++) {
                if (!input.hasNextLine()) {
                    System.out.println("Enter three animal and delay lines.");
                    return;
                }
                String[] parts = input.nextLine().strip().toLowerCase(Locale.ROOT).split("\s+");
                if (parts.length != 2 || !SOUNDS.containsKey(parts[0])) {
                    System.out.println("Use a known animal and one delay.");
                    return;
                }
                int delay;
                try {
                    delay = Integer.parseInt(parts[1]);
                } catch (NumberFormatException malformed) {
                    System.out.println("Use a whole delay from 0 to 1000.");
                    return;
                }
                if (delay < 0 || delay > 1000) {
                    System.out.println("Use a whole delay from 0 to 1000.");
                    return;
                }
                var recording = new ArrayList<String>();
                names.add(parts[0]);
                recordings.add(recording);
                workers.add(new Thread(new AnimalPlayer(SOUNDS.get(parts[0]), delay, recording::add)));
            }
        }
        workers.forEach(Thread::start);
        try {
            for (Thread worker : workers) {
                worker.join();
            }
        } catch (InterruptedException interrupted) {
            workers.forEach(Thread::interrupt);
            throw interrupted;
        }
        for (int index = 0; index < names.size(); index++) {
            System.out.println(names.get(index) + ": " + recordings.get(index));
        }
    }
}

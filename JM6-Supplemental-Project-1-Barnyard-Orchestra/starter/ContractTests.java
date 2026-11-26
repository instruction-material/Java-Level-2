import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

class ContractTests {
    public static void main(String[] args) throws InterruptedException {
        List<String> cow = new ArrayList<>();
        List<String> frog = new ArrayList<>();
        var first = new Thread(new AnimalPlayer("moo", 0, cow::add));
        var second = new Thread(new AnimalPlayer("ribbit", 0, frog::add));
        first.start();
        second.start();
        finish(first);
        finish(second);
        check(cow.equals(List.of("moo", "moo")) && frog.equals(List.of("ribbit", "ribbit")), "each worker records two sounds");

        AtomicBoolean preserved = new AtomicBoolean();
        List<String> interruptedSounds = new ArrayList<>();
        var player = new AnimalPlayer("oink", 0, sound -> {
            interruptedSounds.add(sound);
            Thread.currentThread().interrupt();
        });
        var cancelled = new Thread(() -> {
            player.run();
            preserved.set(Thread.currentThread().isInterrupted());
        });
        cancelled.start();
        finish(cancelled);
        check(preserved.get() && interruptedSounds.equals(List.of("oink")), "interrupted player records one sound");
        System.out.println("Orchestra checks passed");
    }

    private static void finish(Thread thread) throws InterruptedException {
        thread.join(3000);
        if (thread.isAlive()) {
            thread.interrupt();
            thread.join(3000);
            throw new AssertionError("Worker did not finish");
        }
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}

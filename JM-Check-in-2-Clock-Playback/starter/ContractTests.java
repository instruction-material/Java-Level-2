import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

class ContractTests {
    public static void main(String[] args) throws InterruptedException {
        var hours = new ArrayList<Integer>();
        var completed = new Thread(new ClockPlayback(11, 4, 0, hours::add));
        completed.start();
        finish(completed);
        check(hours.equals(List.of(11, 12, 1, 2)), "four ordered clock readings");

        var interrupted = new AtomicBoolean();
        var firstOnly = new ArrayList<Integer>();
        var playback = new ClockPlayback(7, 8, 0, hour -> {
            firstOnly.add(hour);
            Thread.currentThread().interrupt();
        });
        var cancelled = new Thread(() -> {
            playback.run();
            interrupted.set(Thread.currentThread().isInterrupted());
        });
        cancelled.start();
        finish(cancelled);
        check(firstOnly.equals(List.of(7)) && interrupted.get(), "cancelled worker restores interrupt");
        System.out.println("Clock playback checks passed");
    }

    private static void finish(Thread worker) throws InterruptedException {
        worker.join(3000);
        if (worker.isAlive()) {
            worker.interrupt();
            worker.join(3000);
            throw new AssertionError("Worker did not stop.");
        }
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }
}

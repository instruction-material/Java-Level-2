import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

class ContractTests {
    public static void main(String[] args) throws InterruptedException {
        List<String> frames = new ArrayList<>();
        Thread normal = new Thread(new SimpleAnimation(4, 7, 0, frames::add));
        normal.start();
        finish(normal);
        check(frames.equals(List.of("0...", ".0..", "..0.", "...0", "..0.", ".0..", "0...")), "seven-frame bounce");

        AtomicBoolean preserved = new AtomicBoolean();
        List<String> cancelledFrames = new ArrayList<>();
        Runnable animation = new SimpleAnimation(2, 6, 0, frame -> {
            cancelledFrames.add(frame);
            Thread.currentThread().interrupt();
        });
        Thread cancelled = new Thread(() -> {
            animation.run();
            preserved.set(Thread.currentThread().isInterrupted());
        });
        cancelled.start();
        finish(cancelled);
        check(preserved.get() && cancelledFrames.equals(List.of("0.")), "interrupt stops after first frame and stays set");
        System.out.println("Animation checks passed");
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

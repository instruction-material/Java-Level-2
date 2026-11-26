import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

class ContractTests {
    public static void main(String[] args) throws InterruptedException {
        var available = new GateWorker(new CountDownLatch(1), new CountDownLatch(0), 5000);
        Thread released = new Thread(available);
        released.start();
        finish(released);
        check("Released".equals(available.outcome()), "released signal succeeds");

        var ready = new CountDownLatch(1);
        var blocked = new GateWorker(ready, new CountDownLatch(1), 5000);
        var preserved = new AtomicBoolean();
        Thread cancelled = new Thread(() -> {
            blocked.run();
            preserved.set(Thread.currentThread().isInterrupted());
        });
        cancelled.start();
        try {
            check(ready.await(2, TimeUnit.SECONDS), "worker announced readiness");
        } finally {
            cancelled.interrupt();
            finish(cancelled);
        }
        check("Cancelled".equals(blocked.outcome()) && preserved.get(), "interrupt cancels an unreleased job");

        var expired = new GateWorker(new CountDownLatch(1), new CountDownLatch(1), 0);
        Thread timeout = new Thread(expired);
        timeout.start();
        finish(timeout);
        check("Timed out".equals(expired.outcome()), "unreleased job reaches its deadline");
        System.out.println("Worker gate checks passed");
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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class GateWorker implements Runnable {
    private final CountDownLatch ready;
    private final CountDownLatch release;
    private final long timeoutMillis;
    private String outcome;

    GateWorker(CountDownLatch ready, CountDownLatch release, long timeoutMillis) {
        this.ready = ready;
        this.release = release;
        this.timeoutMillis = timeoutMillis;
    }

    @Override
    public void run() {
        ready.countDown();
        // TODO: Wait for release, handle a timeout, and preserve an interruption.
        outcome = "Pending";
    }

    String outcome() {
        return outcome;
    }
}

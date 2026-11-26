public final class Timer implements Runnable, AutoCloseable {
    private final Thread worker = new Thread(this, "maze-countdown");
    private final long tickMillis;
    private volatile int remaining;

    public Timer() {
        this(30, 1000);
    }

    Timer(int seconds, long tickMillis) {
        if (seconds < 0 || tickMillis < 0) {
            throw new IllegalArgumentException("Use a nonnegative countdown and delay.");
        }
        remaining = seconds;
        this.tickMillis = tickMillis;
    }

    public void init() {
        worker.start();
    }

    @Override
    public void run() {
        while (remaining > 0 && !Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(tickMillis);
            } catch (InterruptedException interrupted) {
                Thread.currentThread().interrupt();
                return;
            }
            if (!Thread.currentThread().isInterrupted()) {
                remaining--;
            }
        }
    }

    public int getTime() {
        return remaining;
    }

    public void stop() {
        worker.interrupt();
    }

    boolean isRunning() {
        return worker.isAlive();
    }

    @Override
    public void close() {
        stop();
        boolean interrupted = false;
        while (worker.isAlive()) {
            try {
                worker.join();
            } catch (InterruptedException interruptedWhileJoining) {
                interrupted = true;
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
    }
}

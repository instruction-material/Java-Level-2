import java.util.concurrent.CountDownLatch;

class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("First job: " + launch(true));
        System.out.println("Second job: " + launch(false));
    }

    private static String launch(boolean shouldRelease) throws InterruptedException {
        var ready = new CountDownLatch(1);
        var release = new CountDownLatch(1);
        var job = new GateWorker(ready, release, 5000);
        var thread = new Thread(job);
        thread.start();
        try {
            ready.await();
            if (shouldRelease) {
                release.countDown();
            } else {
                thread.interrupt();
            }
            thread.join();
            return job.outcome();
        } catch (InterruptedException interrupted) {
            thread.interrupt();
            throw interrupted;
        }
    }
}

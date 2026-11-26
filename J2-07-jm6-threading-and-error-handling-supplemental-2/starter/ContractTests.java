class ContractTests {
    public static void main(String[] args) throws InterruptedException {
        int[] data = {-8, -1, -3};
        var cold = new TemperatureBatch(data);
        var empty = new TemperatureBatch(new int[0]);
        data[0] = 21;
        Thread a = new Thread(cold);
        Thread b = new Thread(empty);
        a.start();
        b.start();
        finish(a);
        finish(b);
        check("min -8, max -1".equals(cold.summary()), "worker uses its own copy and accepts negative readings");
        check("No readings.".equals(empty.summary()), "empty batch");
        var single = new TemperatureBatch(new int[] {7});
        single.run();
        check("min 7, max 7".equals(single.summary()), "single reading");
        System.out.println("Weather batch checks passed");
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

class Main {
    public static void main(String[] args) throws InterruptedException {
        var hill = new TemperatureBatch(new int[] {1, -3, 5, 2});
        var lake = new TemperatureBatch(new int[] {7});
        var first = new Thread(hill);
        var second = new Thread(lake);
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println("Hill: " + hill.summary());
        System.out.println("Lake: " + lake.summary());
    }
}

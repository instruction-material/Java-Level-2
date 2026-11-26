class Main {
    public static void main(String[] args) {
        int[] numbers = {4, 9, 15};
        for (int divisor : new int[] {3, 0, 2}) {
            System.out.println(Recovery.divide(12, divisor));
        }
        for (int index : new int[] {1, -1, 3, 2}) {
            System.out.println(Recovery.lookup(numbers, index));
        }
    }
}

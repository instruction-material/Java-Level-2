class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Sum: " + Calculator.arithmetic("add", 10, 2));
            System.out.println("5!: " + Calculator.factorial(5));
            perform(new String[] {"divide", "5", "0"});
        } else {
            perform(args);
        }
    }

    private static void perform(String[] args) {
        try {
            if (args.length == 2 && args[0].equals("factorial")) {
                System.out.println(Calculator.factorial(Integer.parseInt(args[1])));
            } else if (args.length == 3) {
                System.out.println(Calculator.arithmetic(args[0], Double.parseDouble(args[1]), Double.parseDouble(args[2])));
            } else {
                System.out.println("Use an operation and its operands.");
            }
        } catch (NumberFormatException malformed) {
            System.out.println("Invalid number.");
        } catch (IllegalArgumentException rejected) {
            System.out.println(rejected.getMessage());
        }
    }
}

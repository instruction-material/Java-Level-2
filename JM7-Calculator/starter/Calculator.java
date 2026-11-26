final class Calculator {
    static double arithmetic(String operation, double first, double second) {
        if (!Double.isFinite(first) || !Double.isFinite(second)) {
            throw new IllegalArgumentException("Use finite operands.");
        }
        if ((operation.equals("divide") || operation.equals("remainder")) && second == 0) {
            throw new IllegalArgumentException("Divide by zero.");
        }
        double result = switch (operation) {
            case "add" -> first + second;
            case "subtract" -> first - second;
            case "multiply" -> first * second;
            case "divide" -> first / second;
            case "remainder" -> first % second;
            case "power" -> Math.pow(first, second);
            default -> throw new IllegalArgumentException("Unknown operation.");
        };
        if (!Double.isFinite(result)) throw new IllegalArgumentException("Result is outside the supported range.");
        return result;
    }

    static long factorial(int whole) {
        if (whole < 0 || whole > 20) throw new IllegalArgumentException("Use a whole number from 0 to 20.");
        long product = 1;
        // TODO: Multiply the factors from 2 through the supplied number.
        return product;
    }
}

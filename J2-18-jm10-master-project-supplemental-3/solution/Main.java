class Main {
    public static void main(String[] args) {
        var commands = new Commands();
        commands.register("double", value -> value * 2);
        commands.register("next", value -> value + 1);
        System.out.println("Double: " + commands.run("Double", 7));
        System.out.println("Next: " + commands.run("next", 7));
    }
}

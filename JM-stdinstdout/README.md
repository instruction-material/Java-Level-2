# Standard Input and Output

The program reads one whole line as UTF-8 and writes that line to standard output. Spaces are part of the line. Closing input before a line arrives ends the program without an answer.

```sh
javac --release 21 Main.java
printf 'red fox\n' | java Main
```

The output is `red fox`. Run `java Main` directly to type a line. The same program accepts a file or another program as its input without hard-coding a path.

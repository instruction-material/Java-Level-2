import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public final class TriviaGame {
    private static final Path QUESTIONS = Path.of("Questions.txt");
    private static final Path SCORES = Path.of("highscores.txt");

    private static Map<String, List<Question>> loadQuestions() throws IOException {
        List<String> lines = Files.readAllLines(QUESTIONS, StandardCharsets.UTF_8);
        if (lines.isEmpty()) throw new IllegalArgumentException("The question file is empty.");
        var questions = new LinkedHashMap<String, List<Question>>();
        for (int index = 0; index < lines.size(); index += 4) {
            if (index + 3 >= lines.size()) throw invalidQuestion(index);
            String category = lines.get(index).strip();
            String prompt = lines.get(index + 1).strip();
            List<String> options = Arrays.stream(lines.get(index + 2).split(",", -1)).map(String::strip).toList();
            String answer = lines.get(index + 3).strip();
            if (category.isEmpty() || prompt.isEmpty() || options.size() < 2
                || options.stream().anyMatch(String::isEmpty) || new HashSet<>(options).size() != options.size()
                || !options.contains(answer)) {
                throw invalidQuestion(index);
            }
            questions.computeIfAbsent(category, key -> new ArrayList<>()).add(new Question(prompt, options, answer));
        }
        return questions;
    }

    private static IllegalArgumentException invalidQuestion(int index) {
        return new IllegalArgumentException("Invalid question starting on line " + (index + 1) + ".");
    }

    public void startGame() {
        try (var input = new Scanner(System.in)) {
            play(input, loadQuestions());
        } catch (IOException unreadable) {
            System.out.println("Cannot read Questions.txt.");
        } catch (IllegalArgumentException invalid) {
            System.out.println(invalid.getMessage());
        }
    }

    private static void play(Scanner input, Map<String, List<Question>> bank) {
        System.out.println("Choose a category: " + bank.keySet());
        List<Question> questions = null;
        String category = "";
        while (questions == null) {
            if (!input.hasNextLine()) {
                System.out.println("Input ended.");
                return;
            }
            category = input.nextLine().strip();
            questions = bank.get(category);
            if (questions == null) System.out.println("Choose a listed category.");
        }

        int score = 0;
        for (Question question : questions) {
            System.out.println(question.getQuestion());
            List<String> options = question.getOptions();
            for (int index = 0; index < options.size(); index++) {
                System.out.println((index + 1) + ". " + options.get(index));
            }
            int answer = readAnswer(input, options.size());
            if (answer < 0) {
                System.out.println("Input ended. Finish all questions to save a score.");
                return;
            }
            if (options.get(answer).equals(question.getCorrectAnswer())) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Correct answer: " + question.getCorrectAnswer());
            }
        }
        System.out.println("Your final score: " + score + "/" + questions.size());
        saveScore(score, category);
    }

    private static int readAnswer(Scanner input, int count) {
        while (true) {
            System.out.println("Enter an answer from 1 to " + count + ":");
            if (!input.hasNextLine()) return -1;
            String line = input.nextLine().strip();
            try {
                int answer = Integer.parseInt(line);
                if (answer >= 1 && answer <= count) return answer - 1;
            } catch (NumberFormatException malformed) {
                System.out.println("Use a whole number.");
            }
        }
    }

    public static void saveScore(int score, String category) {
        if (score < 0 || category == null || category.isBlank() || category.contains("\n") || category.contains("\r")) {
            throw new IllegalArgumentException("Use a category on one line and a nonnegative score.");
        }
        try {
            Files.writeString(SCORES, "Category: " + category + " | Score: " + score + "\n", StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Your score has been saved.");
        } catch (IOException failure) {
            System.out.println("Cannot save the score.");
        }
    }

    public static void showHighScores() {
        try (var reader = Files.newBufferedReader(SCORES, StandardCharsets.UTF_8)) {
            System.out.println("High Scores:");
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                System.out.println(line);
            }
        } catch (NoSuchFileException missing) {
            System.out.println("No saved scores.");
        } catch (IOException failure) {
            System.out.println("Cannot read saved scores.");
        }
    }
}

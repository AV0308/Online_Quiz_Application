import java.util.Arrays;
import java.util.Scanner;

/**
 * Entry point. Wires the QuestionBank, QuizEngine and ScoreEvaluator
 * together into a runnable console quiz.
 */
public class QuizApplication {

    public static void main(String[] args) {
        QuestionBank bank = buildQuestionBank();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("========================================");
            System.out.println("     WELCOME TO THE AV QUIZ SYSTEM        ");
            System.out.println("========================================");
            System.out.print("Enter your name: ");
            String name = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
            if (name.isEmpty()) {
                name = "Guest";
            }

            QuizEngine engine = new QuizEngine(bank, scanner);
            QuizAttempt attempt = engine.run(name);

            ScoreEvaluator evaluator = new ScoreEvaluator();
            Result result = evaluator.evaluate(attempt, bank);
            result.display();
        }
    }

    /** Seeds a small sample bank. Swap this out for a file/DB-backed loader in a real app. */
    private static QuestionBank buildQuestionBank() {
        QuestionBank bank = new QuestionBank();

        bank.addQuestion(new Question(1,
                "Which keyword is used for a class to inherit another class in Java?",
                Arrays.asList("implements", "extends", "inherits", "super"),
                1, Difficulty.EASY));

        bank.addQuestion(new Question(2,
                "Which data structure follows First-In-First-Out (FIFO) order?",
                Arrays.asList("Stack", "Queue", "Tree", "HashMap"),
                1, Difficulty.EASY));

        bank.addQuestion(new Question(3,
                "Which Java collection does not allow duplicate elements?",
                Arrays.asList("List", "Set", "Map", "Array"),
                1, Difficulty.MEDIUM));

        bank.addQuestion(new Question(4,
                "What is the average time complexity of binary search?",
                Arrays.asList("O(n)", "O(n log n)", "O(log n)", "O(1)"),
                2, Difficulty.MEDIUM));

        bank.addQuestion(new Question(5,
                "Which keyword prevents a class from being subclassed?",
                Arrays.asList("static", "final", "private", "abstract"),
                1, Difficulty.MEDIUM));

        bank.addQuestion(new Question(6,
                "Which of these is an unchecked exception in Java?",
                Arrays.asList("IOException", "SQLException", "NullPointerException", "ClassNotFoundException"),
                2, Difficulty.HARD));

        bank.addQuestion(new Question(7,
                "Which interface must a class implement to be used in a for-each loop?",
                Arrays.asList("Iterable", "Comparable", "Serializable", "Cloneable"),
                0, Difficulty.HARD));

        return bank;
    }
}

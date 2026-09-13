import java.util.List;
import java.util.Scanner;

/**
 * Orchestrates the quiz session: shows each question, reads and validates
 * the candidate's input, and builds up a QuizAttempt. Contains no scoring
 * logic itself - that's ScoreEvaluator's job.
 */
public class QuizEngine {
    private final QuestionBank questionBank;
    private final Scanner scanner;

    public QuizEngine(QuestionBank questionBank, Scanner scanner) {
        this.questionBank = questionBank;
        this.scanner = scanner;
    }

    public QuizAttempt run(String candidateName) {
        List<Question> questions = questionBank.getShuffledQuestions();
        QuizAttempt attempt = new QuizAttempt(candidateName);
        attempt.start();

        System.out.println("\nHi " + candidateName + "! This quiz has " + questions.size() + " questions.");
        System.out.println("Type the option number to answer, or 0 to skip a question.\n");

        int questionNumber = 1;
        for (Question question : questions) {
            displayQuestion(questionNumber, question);
            int selectedIndex = captureAnswer(question);
            attempt.recordAnswer(new Answer(question.getId(), selectedIndex));
            questionNumber++;
        }

        attempt.finish();
        return attempt;
    }

    private void displayQuestion(int number, Question question) {
        System.out.println("Q" + number + ". " + question.getText() + "  [" + question.getDifficulty() + "]");
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + options.get(i));
        }
    }

    private int captureAnswer(Question question) {
        int optionCount = question.getOptions().size();
        while (true) {
            System.out.print("Your answer (1-" + optionCount + ", 0 to skip): ");
            String line = scanner.hasNextLine() ? scanner.nextLine().trim() : "0";
            try {
                int choice = validateInput(line, optionCount);
                System.out.println();
                return choice == 0 ? -1 : choice - 1;
            } catch (InvalidAnswerException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    private int validateInput(String line, int optionCount) throws InvalidAnswerException {
        int choice;
        try {
            choice = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new InvalidAnswerException("Please enter a whole number.");
        }
        if (choice < 0 || choice > optionCount) {
            throw new InvalidAnswerException("Enter a value between 0 and " + optionCount + ".");
        }
        return choice;
    }
}

/**
 * Thrown when a candidate types something that isn't a valid option number
 * for the current question. Kept as a checked exception so the engine is
 * forced to handle bad input explicitly rather than letting it crash the quiz.
 */
public class InvalidAnswerException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidAnswerException(String message) {
        super(message);
    }
}

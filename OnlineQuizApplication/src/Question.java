import java.util.Collections;
import java.util.List;

/**
 * Immutable representation of a single multiple-choice question.
 * Options are stored 0-indexed internally; the console UI shows them 1-indexed.
 */
public final class Question {
    private final int id;
    private final String text;
    private final List<String> options;
    private final int correctOptionIndex;
    private final Difficulty difficulty;

    public Question(int id, String text, List<String> options, int correctOptionIndex, Difficulty difficulty) {
        if (options == null || options.size() < 2) {
            throw new IllegalArgumentException("Question " + id + " needs at least 2 options.");
        }
        if (correctOptionIndex < 0 || correctOptionIndex >= options.size()) {
            throw new IllegalArgumentException("Question " + id + " has an out-of-range correct option index.");
        }
        this.id = id;
        this.text = text;
        this.options = Collections.unmodifiableList(options);
        this.correctOptionIndex = correctOptionIndex;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getMaxPoints() {
        return difficulty.getPoints();
    }

    public boolean isCorrect(int selectedIndex) {
        return selectedIndex == correctOptionIndex;
    }
}

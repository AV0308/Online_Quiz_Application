/**
 * Difficulty level of a question. Each level carries its own point value,
 * so harder questions are worth more when the score is calculated.
 */
public enum Difficulty {
    EASY(5),
    MEDIUM(10),
    HARD(15);

    private final int points;

    Difficulty(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}

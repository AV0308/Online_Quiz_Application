import java.util.ArrayList;
import java.util.List;

/**
 * Represents one candidate's attempt at the quiz: the answers they gave,
 * in order, plus how long the attempt took.
 */
public class QuizAttempt {
    private final String candidateName;
    private final List<Answer> answers = new ArrayList<>();
    private long startTimeMillis;
    private long endTimeMillis;

    public QuizAttempt(String candidateName) {
        this.candidateName = candidateName;
    }

    public void start() {
        startTimeMillis = System.currentTimeMillis();
    }

    public void finish() {
        endTimeMillis = System.currentTimeMillis();
    }

    public void recordAnswer(Answer answer) {
        answers.add(answer);
    }

    public String getCandidateName() {
        return candidateName;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public long getDurationSeconds() {
        return Math.max(0, (endTimeMillis - startTimeMillis) / 1000);
    }
}

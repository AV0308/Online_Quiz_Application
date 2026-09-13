import java.util.List;

/**
 * Pure scoring logic, kept separate from both the question data and the
 * console I/O so it can be unit tested or reused (e.g. behind a web API)
 * without touching Scanner/System.out at all.
 */
public class ScoreEvaluator {

    public Result evaluate(QuizAttempt attempt, QuestionBank bank) {
        int correct = 0;
        int incorrect = 0;
        int skipped = 0;
        int scoreObtained = 0;
        int maxPossibleScore = 0;

        List<Answer> answers = attempt.getAnswers();

        for (Answer answer : answers) {
            Question question = bank.getById(answer.getQuestionId());
            if (question == null) {
                continue; // defensive: ignore answers for unknown questions
            }

            maxPossibleScore += question.getMaxPoints();

            if (answer.isSkipped()) {
                skipped++;
            } else if (question.isCorrect(answer.getSelectedOptionIndex())) {
                correct++;
                scoreObtained += question.getMaxPoints();
            } else {
                incorrect++;
            }
        }

        return new Result(
                attempt.getCandidateName(),
                answers.size(),
                correct,
                incorrect,
                skipped,
                scoreObtained,
                maxPossibleScore,
                attempt.getDurationSeconds()
        );
    }
}

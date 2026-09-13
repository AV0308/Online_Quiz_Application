/**
 * Final, immutable outcome of a scored quiz attempt: raw counts, the score,
 * derived percentage/grade, and a formatted console report.
 */
public class Result {
    private final String candidateName;
    private final int totalQuestions;
    private final int correctCount;
    private final int incorrectCount;
    private final int skippedCount;
    private final int scoreObtained;
    private final int maxPossibleScore;
    private final long durationSeconds;

    public Result(String candidateName, int totalQuestions, int correctCount, int incorrectCount,
                  int skippedCount, int scoreObtained, int maxPossibleScore, long durationSeconds) {
        this.candidateName = candidateName;
        this.totalQuestions = totalQuestions;
        this.correctCount = correctCount;
        this.incorrectCount = incorrectCount;
        this.skippedCount = skippedCount;
        this.scoreObtained = scoreObtained;
        this.maxPossibleScore = maxPossibleScore;
        this.durationSeconds = durationSeconds;
    }

    public double getPercentage() {
        return maxPossibleScore == 0 ? 0.0 : (scoreObtained * 100.0) / maxPossibleScore;
    }

    public Grade getGrade() {
        return Grade.fromPercentage(getPercentage());
    }

    public void display() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("               QUIZ RESULT              ");
        System.out.println("========================================");
        System.out.printf("Candidate       : %s%n", candidateName);
        System.out.printf("Total Questions : %d%n", totalQuestions);
        System.out.printf("Correct Answers : %d%n", correctCount);
        System.out.printf("Wrong Answers   : %d%n", incorrectCount);
        System.out.printf("Skipped         : %d%n", skippedCount);
        System.out.printf("Score           : %d / %d%n", scoreObtained, maxPossibleScore);
        System.out.printf("Percentage      : %.2f%%%n", getPercentage());
        System.out.printf("Grade           : %s%n", getGrade());
        System.out.printf("Remark          : %s%n", getGrade().getRemark());
        System.out.printf("Time Taken      : %d sec%n", durationSeconds);
        System.out.println("========================================");
    }
}

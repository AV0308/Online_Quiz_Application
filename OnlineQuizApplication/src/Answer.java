/**
 * A single user response: which question, and which option index was picked.
 * selectedOptionIndex of -1 means the question was skipped.
 */
public final class Answer {
    private final int questionId;
    private final int selectedOptionIndex;

    public Answer(int questionId, int selectedOptionIndex) {
        this.questionId = questionId;
        this.selectedOptionIndex = selectedOptionIndex;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getSelectedOptionIndex() {
        return selectedOptionIndex;
    }

    public boolean isSkipped() {
        return selectedOptionIndex == -1;
    }
}

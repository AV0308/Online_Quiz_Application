/**
 * Grade band derived from the final percentage score.
 * fromPercentage() walks the constants in declared order (highest
 * threshold first) and returns the first band the score qualifies for.
 */
public enum Grade {
    A(90, "Outstanding! You've mastered this material."),
    B(75, "Great job! Well above average."),
    C(60, "Good effort, but there's room to improve."),
    D(40, "You passed, but review the topics you missed."),
    F(0, "Needs significant improvement. Keep practicing!");

    private final int minPercentage;
    private final String remark;

    Grade(int minPercentage, String remark) {
        this.minPercentage = minPercentage;
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public static Grade fromPercentage(double percentage) {
        for (Grade grade : values()) {
            if (percentage >= grade.minPercentage) {
                return grade;
            }
        }
        return F;
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Stores all quiz questions keyed by id for O(1) lookup during scoring,
 * while preserving insertion order for display purposes.
 */
public class QuestionBank {
    private final Map<Integer, Question> questions = new LinkedHashMap<>();

    public void addQuestion(Question question) {
        questions.put(question.getId(), question);
    }

    public Question getById(int id) {
        return questions.get(id);
    }

    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions.values());
    }

    /** Returns a shuffled copy so each quiz attempt sees questions in a fresh order. */
    public List<Question> getShuffledQuestions() {
        List<Question> list = getAllQuestions();
        Collections.shuffle(list);
        return list;
    }

    /** Demonstrates stream-based filtering: pull every question of a given difficulty. */
    public List<Question> getQuestionsByDifficulty(Difficulty difficulty) {
        return questions.values().stream()
                .filter(q -> q.getDifficulty() == difficulty)
                .collect(Collectors.toList());
    }

    public int size() {
        return questions.size();
    }
}

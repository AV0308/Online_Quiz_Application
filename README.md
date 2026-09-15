# Online Quiz Application (Java)

A console-based MCQ quiz system with automatic scoring and grading, built
around a clean, testable object-oriented design.

## How to run

```bash
cd src
javac *.java
java QuizApplication
```

You'll be asked for your name, then walked through each question. Type the
option number to answer, or `0` to skip. At the end you get a full score
report with a grade and remark.

## Design overview

The app is split into small, single-responsibility classes rather than one
big `main` method, so each concern can be tested or swapped independently:

| Class | Responsibility |
|---|---|
| `Question` | Immutable question: text, options, correct answer, difficulty |
| `Difficulty` (enum) | EASY / MEDIUM / HARD, each with its own point value |
| `QuestionBank` | Stores questions in a `Map<Integer, Question>` for O(1) lookup; can shuffle or filter by difficulty |
| `Answer` | One response: question id + selected option (or skipped) |
| `QuizAttempt` | One candidate's full run: their answers + timing |
| `QuizEngine` | Console I/O — displays questions, reads and validates input |
| `InvalidAnswerException` | Checked exception for bad input (non-numeric, out of range) |
| `ScoreEvaluator` | Pure scoring logic — turns a `QuizAttempt` into a `Result` |
| `Result` | Final report: counts, score, percentage, grade, formatted output |
| `Grade` (enum) | A–F bands derived from percentage, each with a remark |
| `QuizApplication` | `main` — wires everything together and seeds sample questions |

Key points:
- **Data structures**: `Map` for O(1) question lookup by id, `List` for
  ordered options/answers, `Collections.shuffle` for randomized question
  order per attempt, a stream-based filter (`getQuestionsByDifficulty`).
- **Scoring is decoupled from I/O**: `ScoreEvaluator` never touches
  `Scanner`/`System.out`, so it could be reused behind a REST API or in a
  unit test without changes.
- **Validation via checked exceptions**: bad input never crashes the quiz —
  `QuizEngine` catches `InvalidAnswerException` and re-prompts.
- **Extensible**: add more questions in `QuizApplication.buildQuestionBank()`,
  or swap that method for a file/DB-backed loader without touching any other
  class.

## Sample question bank

7 sample Java/CS questions across all three difficulty levels are pre-loaded
so the app runs out of the box. Replace or extend them freely.

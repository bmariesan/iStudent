package ro.ubb.istudent.grading.exam;

import javax.annotation.concurrent.Immutable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Immutable
public class GradingExamService {

    public Double getTotalScoreFromExam(final Exam exam) {
        return exam.getTotalScore();
    }

    public List<Exercise> getRightAnswersFromExam(final Exam exam) {
        return exam.getCorrectExercises().stream()
                .map(x -> new VerifiedExercise(x)).collect(Collectors.toList());
    }
}

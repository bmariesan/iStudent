package ro.ubb.istudent.grading.exam;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.Collections;
import java.util.List;

@Immutable
public class GradingExamService {

    public Double getTotalScoreFromExam(final Exam exam) {
        return exam.getTotalScore();
    }

    public List<String> getRightAnswersFromExam(final Exam exam) {
        // TODO Implement This!
        return Collections.emptyList();
    }

    public List<String> getWrongAnswersFromExam(final Exam exam) {
        // TODO Implement This
        return Collections.emptyList();
    }
}

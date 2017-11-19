package ro.ubb.istudent.util.grading.exam;

import ro.ubb.istudent.domain.Exam;
import ro.ubb.istudent.domain.Exercise;

import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ExamGrader<T extends Exercise> {

    private final List<Exam<T>> exams;

    public ExamGrader(final List<Exam<T>> exams) {
        this.exams = exams;
    }

    public Double getTotalScore() {
        return exams.stream().mapToDouble(Exam::getTotalScore).sum();
    }
}

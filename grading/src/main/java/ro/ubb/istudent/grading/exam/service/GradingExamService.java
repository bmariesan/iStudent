package ro.ubb.istudent.grading.exam.service;

import ro.ubb.istudent.grading.exam.domain.Answer;
import ro.ubb.istudent.grading.exam.domain.CheckedAnswer;
import ro.ubb.istudent.grading.exam.domain.Exam;

import javax.annotation.concurrent.Immutable;
import java.util.List;
import java.util.stream.Collectors;

@Immutable
public class GradingExamService {

    public Double getTotalScoreFromExam(final Exam exam) {
        return exam.getTotalScore();
    }

    public List<Answer> getRightAnswersFromExam(final Exam exam) {
        return exam.getCorrectExercises().stream()
                .map(CheckedAnswer::new)
                .collect(Collectors.toList());
    }
}

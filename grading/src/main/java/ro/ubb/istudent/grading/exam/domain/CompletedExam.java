package ro.ubb.istudent.grading.exam.domain;

import java.util.List;

public interface CompletedExam extends Exam {
    Double totalScore();
    List<Exercise> correctExercises();
    List<Exercise> partiallyCorrectExercises();
}

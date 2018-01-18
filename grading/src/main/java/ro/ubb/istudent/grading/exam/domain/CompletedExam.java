package ro.ubb.istudent.grading.exam.domain;

import ro.ubb.istudent.grading.gradingbook.domain.Student;

import java.util.List;

public interface CompletedExam extends Exam {
    Double totalScore();
    List<Exercise> correctExercises();
    List<Exercise> partiallyCorrectExercises();
    Student completedBy();
}

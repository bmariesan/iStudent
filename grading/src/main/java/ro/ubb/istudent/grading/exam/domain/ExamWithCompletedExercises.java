package ro.ubb.istudent.grading.exam.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.ubb.istudent.grading.gradingbook.domain.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

@ToString
@EqualsAndHashCode(of = {"id"})
@Document(collection = "exam")
public class ExamWithCompletedExercises implements CompletedExam {

    @Id
    @JsonProperty
    private final ObjectId id;

    @JsonProperty
    private final List<CompletedExercise> exercises;

    @JsonProperty
    private final NormalStudent student;

    @JsonProperty
    private final NormalTeacher teacher;

    public ExamWithCompletedExercises() {
        this(ObjectId.get(), new ArrayList<>());
    }

    public ExamWithCompletedExercises(
            final ObjectId id, final List<CompletedExercise> exercises) {
        this(id, exercises, null, null);
    }

    public ExamWithCompletedExercises(
            final ObjectId id,
            final List<CompletedExercise> exercises,
            final NormalStudent student,
            final NormalTeacher teacher) {
        this.id = id;
        this.exercises = exercises;
        this.student = student;
        this.teacher = teacher;
    }

    @Override
    public ObjectId id() {
        return id;
    }

    @Override
    public Teacher createdBy() {
        return teacher;
    }

    @Override
    public Double totalScore() {
        return exercises.stream()
                .mapToDouble(Exercise::score).sum();
    }

    public List<Exercise> correctExercises() {
        return exercises.stream()
                .filter(exercise -> exercise.rightAnswersFromUser()
                        .containsAll(exercise.rightAnswers()))
                .collect(Collectors.toList());
    }

    public List<Exercise> partiallyCorrectExercises() {
        return exercises.stream()
                .filter(exercise -> exercise.rightAnswersFromUser().size() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Student completedBy() {
        return student;
    }
}

package ro.ubb.istudent.grading.exam;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.ubb.istudent.grading.criteria.GradingCriteriaComponent;
import ro.ubb.istudent.grading.exception.StudentNotFound;
import ro.ubb.istudent.grading.gradingbook.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

@ToString
@EqualsAndHashCode(of = {"id"})
@Document(collection = "exam")
public class UnitOfWorkWithCompletedExercises implements CompletedUnitOfWork {

    @Id
    @JsonProperty
    private final ObjectId id;

    @JsonProperty
    private final List<Exercise> exercises;

    @JsonProperty
    private final User student;

    @JsonProperty
    private final GradingCriteriaComponent gradingCriteriaComponent;

    public UnitOfWorkWithCompletedExercises() {
        this(ObjectId.get(), new ArrayList<>(), null, null);
    }

    public UnitOfWorkWithCompletedExercises(
            final ObjectId id,
            final List<Exercise> exercises,
            final User student,
            final GradingCriteriaComponent gradingCriteriaComponent) {
        this.id = id;
        this.exercises = exercises;
        this.student = student;
        this.gradingCriteriaComponent = gradingCriteriaComponent;
    }

    public UnitOfWorkWithCompletedExercises(
            final ObjectId id, final List<Exercise> exercises) {
        this(id, exercises, null, new GradingCriteriaComponent());
    }

    public UnitOfWorkWithCompletedExercises(
            final List<Exercise> exercises, final User student) {
        this(ObjectId.get(), exercises, student, new GradingCriteriaComponent());
    }

    public UnitOfWorkWithCompletedExercises(
            final ObjectId id,
            final List<Exercise> exercises,
            final User student) {
        this(id, exercises, student, new GradingCriteriaComponent());
    }

    public UnitOfWorkWithCompletedExercises(final List<Exercise> exercises) {
        this(ObjectId.get(), exercises);
    }

    public UnitOfWorkWithCompletedExercises(
            List<Exercise> exercises,
            User student,
            GradingCriteriaComponent gradingCriteriaComponent) {
        this(ObjectId.get(), exercises, student, gradingCriteriaComponent);
    }

    @Override
    public ObjectId id() {
        return id;
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
    public Optional<User> fromStudent() {
        return Optional.ofNullable(student);
    }

    @Override
    public User student() {
        return fromStudent().orElseThrow(StudentNotFound::new);
    }

    @Override
    public GradingCriteriaComponent gradingCriteriaComponent() {
        return gradingCriteriaComponent;
    }
}

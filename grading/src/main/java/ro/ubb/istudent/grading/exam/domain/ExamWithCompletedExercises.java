package ro.ubb.istudent.grading.exam.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

@EqualsAndHashCode(of = {"id"})
@Document(collection = "exam")
@ToString(of = {"id", "exercises"})
public class ExamWithCompletedExercises implements CompletedExam {

    @Id
    @JsonProperty
    private final ObjectId id;

    @JsonProperty
    private final List<Exercise> exercises;

    public ExamWithCompletedExercises() {
        this(ObjectId.get(), new ArrayList<>());
    }

    public ExamWithCompletedExercises(final ObjectId id, final List<Exercise> exercises) {
        this.id = id;
        this.exercises = exercises;
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
}

package ro.ubb.istudent.grading.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.concurrent.Immutable;
import org.bson.types.ObjectId;

import java.util.List;

@Immutable
public class VerifiedExercise implements Exercise {

    @JsonIgnore
    private final Exercise exercise;

    public VerifiedExercise(final Exercise exercise) {
        this.exercise = exercise;
    }

    @JsonProperty
    public ObjectId id() {
        return exercise.getId();
    }

    @JsonProperty
    public List<String> rightAnswers() {
        return exercise.getUserAnswers();
    }

    public List<String> getAllAnswers() {
        return exercise.getQuestion().allAnswers();
    }

    @Override
    public Double getScore() {
        return exercise.getScore();
    }

    @Override
    public Question getQuestion() {
        return exercise.getQuestion();
    }

    @Override
    public List<String> getUserAnswers() {
        return exercise.getUserAnswers();
    }

    @Override
    public Boolean isRight() {
        return exercise.isRight();
    }

    @Override
    public ObjectId getId() {
        return exercise.getId();
    }
}

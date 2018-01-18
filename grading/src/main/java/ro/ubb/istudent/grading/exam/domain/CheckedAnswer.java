package ro.ubb.istudent.grading.exam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;

import javax.annotation.concurrent.Immutable;
import java.util.List;

@Immutable
@ToString(of={"exerciseId"})
@EqualsAndHashCode(of = {"exercise"})
public class CheckedAnswer implements Answer {

    @JsonIgnore
    private final Exercise exercise;

    public CheckedAnswer(final Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    @JsonProperty
    public ObjectId exerciseId() {
        return exercise.id();
    }

    @Override
    @JsonProperty
    public List<String> rightAnswersFromUserInExercise() {
        return exercise.userAnswers();
    }

    @Override
    @JsonProperty
    public List<String> allRightAnswersFromExercise() {
        return exercise.question().allAnswers();
    }
}

package ro.ubb.istudent.grading.exam.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.concurrent.Immutable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Immutable
@ToString
@EqualsAndHashCode(of = {"id"})
@Document(collection = "completed-exercise")
public class CompletedExercise implements Exercise {

    @Id
    @JsonProperty
    private final ObjectId id;

    @JsonProperty
    private final Question question;

    @JsonProperty
    private final List<String> answers;

    public CompletedExercise() {
        this(ObjectId.get(), new ChoiceQuestion(), Collections.emptyList());
    }

    public CompletedExercise(
            final Question question,
            final List<String> answers) {
        this(ObjectId.get(), question, answers);
    }

    public CompletedExercise(
            final ObjectId id,
            final Question question,
            final List<String> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public ObjectId id() {
        return id;
    }

    @Override
    public Question question() {
        return this.question;
    }

    @Override
    public Double score() {
        return question.isCorrect(answers) ?
                question.points() : 0.0;
    }

    @Override
    public Boolean isRight() {
        return question.isCorrect(answers);
    }

    @Override
    public List<String> userAnswers() {
        return answers.stream()
                .filter(x -> question.answers().contains(x))
                .collect(Collectors.toList());
    }
}

package ro.ubb.istudent.grading.exam;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "question", "answers"})
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
    public List<String> rightAnswers() {
        return question.answers();
    }

    @Override
    public List<String> rightAnswersFromUser() {
        return question.answers().stream()
                .filter(answers::contains)
                .collect(Collectors.toList());
    }

    @Override
    public Double score() {
        return question.isCorrect(answers) ?
                question.points() : 0.0;
    }
}

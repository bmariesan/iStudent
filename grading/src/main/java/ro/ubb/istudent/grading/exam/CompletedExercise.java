package ro.ubb.istudent.grading.exam;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.concurrent.Immutable;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Immutable
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

    public ObjectId getId() {
        return id;
    }

    @Override
    public Question getQuestion() {
        return this.question;
    }

    @Override
    public Double getScore() {
        return question.isCorrect(answers) ?
                question.points() : 0.0;
    }

    @Override
    public Boolean isRight() {
        return question.isCorrect(answers);
    }

    @Override
    public List<String> getUserAnswers() {
        return answers.stream()
                .filter(x -> question.answers().contains(x))
                .collect(Collectors.toList());
    }
}

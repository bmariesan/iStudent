package ro.ubb.istudent.grading.exam;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

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
    public Double getScore() {
        return question.isCorrect(answers) ?
                question.points() : 0.0;
    }
}

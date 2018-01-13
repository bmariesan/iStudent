package ro.ubb.istudent.grading.exam;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;

@Immutable
@EqualsAndHashCode(of = "id")
@ToString(doNotUseGetters = true)
@Document(collection = "choice-question")
public class ChoiceQuestion implements Question {

    @Id
    @JsonProperty
    private final ObjectId id;

    @JsonProperty
    private final String text;

    @JsonProperty
    private final List<String> rightAnswers;

    @JsonProperty
    private final List<String> possibleAnswers;

    @JsonProperty
    private final Double points;

    public ChoiceQuestion() {
        this(ObjectId.get(), "", emptyList(), emptyList(), 0.0);
    }

    public ChoiceQuestion(
            final String text,
            final List<String> rightAnswers,
            final List<String> possibleAnswers,
            final Double points) {
        this(ObjectId.get(), text, rightAnswers, possibleAnswers, points);
    }

    public ChoiceQuestion(
            final ObjectId id,
            final String text,
            final List<String> rightAnswers,
            final List<String> possibleAnswers,
            final Double points) {
        this.id = id;
        this.text = text;
        this.rightAnswers = rightAnswers;
        this.possibleAnswers = possibleAnswers;
        this.points = points;
    }

    public ObjectId getId() {
        return id;
    }

    @Override
    public List<String> answers() {
        return rightAnswers;
    }

    @Override
    public Boolean isCorrect(List<String> answers) {
        return answers.size() == rightAnswers.size() ?
                compareGivenAnswers(answers) : false;
    }

    private Boolean compareGivenAnswers(final List<String> possibleAnswers) {
        return IntStream.range(0, possibleAnswers.size())
                .mapToObj(it -> possibleAnswers.get(it).equals(rightAnswers.get(it)))
                .allMatch(it -> it.equals(Boolean.TRUE));
    }

    @Override
    public Double points() {
        return points;
    }
}

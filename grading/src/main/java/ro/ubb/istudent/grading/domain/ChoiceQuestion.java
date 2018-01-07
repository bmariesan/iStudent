package ro.ubb.istudent.grading.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;

@Document(collection = "notcompletedexercise")
public class ChoiceQuestion implements Question{
    @Id
    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    private final String text;
    private final String rightAnswer;
    private final List possibleAnswers;
    private final Double points;

    public ChoiceQuestion(final String text,
                          final String rightAnswer,
                          final List possibleAnswers,
                          final Double points) {
        this.text = text;
        this.rightAnswer = rightAnswer;
        this.possibleAnswers = possibleAnswers;
        this.points = points;
    }

    public String getText() {
        return text;
    }

    public List getPossibleAnswers() {
        return possibleAnswers;
    }

    public Double getPoints() {
        return points;
    }

    @Override
    public Optional<String> getAnswer() {
        return Optional.ofNullable(rightAnswer);
    }
}

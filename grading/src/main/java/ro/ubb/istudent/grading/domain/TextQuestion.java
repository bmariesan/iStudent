package ro.ubb.istudent.grading.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;

@Document(collection = "textquestions")
public class TextQuestion implements Question {
    @Id
    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    private final String text;
    private final Double points;

    public TextQuestion(final String text, final Double points) {
        this.text = text;
        this.points = points;
    }

    public String getText() {
        return text;
    }

    public Double getPoints() {
        return points;
    }

    @Override
    public Optional<String> getAnswer() {
        return Optional.empty();
    }
}

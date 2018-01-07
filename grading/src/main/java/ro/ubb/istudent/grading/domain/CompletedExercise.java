package ro.ubb.istudent.grading.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.ubb.istudent.grading.exception.NoRightAnswer;

import java.io.Serializable;

@Document(collection = "comletedexercise")
public class CompletedExercise implements Exercise, Serializable {
    @Id
    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    private final Question question;
    private final String answer;

    public CompletedExercise(final Question question, final String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    @Override
    public Double getScore() {
        return isCorrect() ? question.getPoints() : 0.0;
    }

    private Boolean isCorrect() {
        return question.getAnswer()
                .orElseThrow(NoRightAnswer::new).equals(answer);
    }
}

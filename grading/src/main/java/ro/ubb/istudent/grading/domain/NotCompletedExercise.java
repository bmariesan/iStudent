package ro.ubb.istudent.grading.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

@Document(collection = "notcompletedexercise")
public class NotCompletedExercise implements Exercise, Serializable {
    @Id
    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    private final Question question;

    public NotCompletedExercise(final Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public CompletedExercise complete(final String withAnswer) {
        return new CompletedExercise(question, withAnswer);
    }

    @Override
    public Double getScore() {
        return 0.0;
    }
}

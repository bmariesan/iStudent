package ro.ubb.istudent.grading.domain;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

public class NotCompletedExercise<T> implements Exercise {

    private final Question<T> question;

    public NotCompletedExercise(final Question<T> question) {
        this.question = question;
    }

    public Question<T> getQuestion() {
        return question;
    }

    public CompletedExercise<T> complete(final T withAnswer) {
        return new CompletedExercise<>(question, withAnswer);
    }

    @Override
    public Double getScore() {
        return 0.0;
    }
}

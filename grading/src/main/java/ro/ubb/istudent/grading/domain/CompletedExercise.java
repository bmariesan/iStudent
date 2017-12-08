package ro.ubb.istudent.grading.domain;

import ro.ubb.istudent.grading.exception.NoRightAnswer;

public class CompletedExercise<T> implements Exercise {

    private final Question<T> question;
    private final T answer;

    public CompletedExercise(final Question<T> question, final T answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question<T> getQuestion() {
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

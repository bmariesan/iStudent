package ro.ubb.istudent.util.grading.exam;

import ro.ubb.istudent.domain.Question;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class QuestionBuilder<T> {

    private String text = "";
    private List possibleAnswers = emptyList();
    private T rightAnswer = null;
    private Double points = 100.0;

    public QuestionBuilder<T> withText(final String text) {
        this.text = text;
        return this;
    }

    public QuestionBuilder<T> withPossibleAnswers(final List possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
        return this;
    }

    public QuestionBuilder<T> withRightAnswer(final T answer) {
        this.rightAnswer = answer;
        return this;
    }

    public QuestionBuilder<T> withPoints(final Double points) {
        this.points = points;
        return this;
    }

    public Question<T> build() {
        return new Question<>(text, rightAnswer, possibleAnswers, points);
    }
}

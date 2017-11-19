package ro.ubb.istudent.domain;

import java.util.List;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

public class Question<T> {

    private final String text;
    private final T rightAnswer;
    private final List possibleAnswers;
    private final Double points;

    public Question(final String text,
             final T rightAnswer,
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

    public T getRightAnswer() {
        return rightAnswer;
    }

    public List getPossibleAnswers() {
        return possibleAnswers;
    }

    public Double getPoints() {
        return points;
    }
}

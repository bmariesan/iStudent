package ro.ubb.istudent.grading.exam;


import ro.ubb.istudent.grading.domain.ChoiceQuestion;
import java.util.List;
import static java.util.Collections.emptyList;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ChoiceQuestionBuilder<T> {

    private String text = "";
    private List possibleAnswers = emptyList();
    private T rightAnswer = null;
    private Double points = 100.0;

    public ChoiceQuestionBuilder<T> withText(final String text) {
        this.text = text;
        return this;
    }

    public ChoiceQuestionBuilder<T> withPossibleAnswers(final List possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
        return this;
    }

    public ChoiceQuestionBuilder<T> withRightAnswer(final T answer) {
        this.rightAnswer = answer;
        return this;
    }

    public ChoiceQuestionBuilder<T> withPoints(final Double points) {
        this.points = points;
        return this;
    }

    public ChoiceQuestion<T> build() {
        return new ChoiceQuestion<>(text, rightAnswer, possibleAnswers, points);
    }
}

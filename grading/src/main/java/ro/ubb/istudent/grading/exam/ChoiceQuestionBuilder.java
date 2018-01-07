package ro.ubb.istudent.grading.exam;


import ro.ubb.istudent.grading.domain.ChoiceQuestion;
import java.util.List;
import static java.util.Collections.emptyList;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ChoiceQuestionBuilder {

    private String text = "";
    private List possibleAnswers = emptyList();
    private String rightAnswer = null;
    private Double points = 100.0;

    public ChoiceQuestionBuilder withText(final String text) {
        this.text = text;
        return this;
    }

    public ChoiceQuestionBuilder withPossibleAnswers(final List possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
        return this;
    }

    public ChoiceQuestionBuilder withRightAnswer(final String answer) {
        this.rightAnswer = answer;
        return this;
    }

    public ChoiceQuestionBuilder withPoints(final Double points) {
        this.points = points;
        return this;
    }

    public ChoiceQuestion build() {
        return new ChoiceQuestion(text, rightAnswer, possibleAnswers, points);
    }
}

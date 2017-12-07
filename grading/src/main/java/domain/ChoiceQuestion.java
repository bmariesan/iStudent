package domain;

import java.util.List;
import java.util.Optional;

public class ChoiceQuestion<T> implements Question<T>{

    private final String text;
    private final T rightAnswer;
    private final List possibleAnswers;
    private final Double points;

    public ChoiceQuestion(final String text,
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

    public List getPossibleAnswers() {
        return possibleAnswers;
    }

    public Double getPoints() {
        return points;
    }

    @Override
    public Optional<T> getAnswer() {
        return Optional.ofNullable(rightAnswer);
    }
}

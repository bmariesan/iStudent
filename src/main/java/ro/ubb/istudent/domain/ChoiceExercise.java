package ro.ubb.istudent.domain;

import java.util.List;
import java.util.function.Function;

public abstract class ChoiceExercise<T> implements Exercise {

    private final String question;
    private final List<String> possibleAnswers;
    private final Integer points;
    private final T answer;

    public ChoiceExercise(String question,
                          List<String> possibleAnswers,
                          Integer points,
                          T answer) {
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.points = points;
        this.answer = answer;
    }

    public ChoiceExercise(String question,
                          List<String> possibleAnswers,
                          Integer points) {
        this(question, possibleAnswers, points, null);
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public Integer getPoints() {
        return points;
    }

}

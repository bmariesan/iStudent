package ro.ubb.istudent.domain;

import java.util.List;

public class MultipleChoiceExercise extends ChoiceExercise {

    private final List<String> answers;

    public MultipleChoiceExercise(String question, List<String> answers, List<String> possibleAnswers, Integer points) {
        super(question, possibleAnswers, points);

        this.answers = answers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    @Override
    public Integer getScore() {
        return null;
    }
}

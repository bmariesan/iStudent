package ro.ubb.istudent.domain;

import java.util.List;

public class SingleChoiceExercise extends ChoiceExercise {
    private final String answer;

    public SingleChoiceExercise(String question, String answer, List<String> possibleAnswers, Integer points) {
        super(question, possibleAnswers, points);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public Integer getScore() {
        return null;
    }
}

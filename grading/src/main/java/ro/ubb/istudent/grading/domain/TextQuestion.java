package ro.ubb.istudent.grading.domain;

import java.util.Optional;

public class TextQuestion implements Question<String> {

    private final String text;
    private final Double points;

    public TextQuestion(final String text, final Double points) {
        this.text = text;
        this.points = points;
    }

    public String getText() {
        return text;
    }

    public Double getPoints() {
        return points;
    }

    @Override
    public Optional<String> getAnswer() {
        return Optional.empty();
    }
}

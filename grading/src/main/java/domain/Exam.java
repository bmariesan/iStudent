package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

public class Exam<T extends Exercise> {

    private final List<T> exercises;

    public Exam(List<T> exercises) {
        this.exercises = exercises;
    }

    public Exam() {
        this(new ArrayList<>());
    }

    public Double getTotalScore() {
        return exercises.stream()
                .mapToDouble(Exercise::getScore).sum();
    }
}

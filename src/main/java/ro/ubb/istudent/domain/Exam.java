package ro.ubb.istudent.domain;

import java.util.ArrayList;
import java.util.List;

import static ro.ubb.istudent.domain.ComponentImportance.HIGH;

/**
 * <p>An object with represents a member of the grading criteria.</p>
 * <p>The object has a type (e.g FINAL_EXAM), importance (e.g OPTIONAL)
 * and a percent (how much the member matters when we calculate the final grade)
 * The importance is used in order to adjust the percentage of the component.
 * The importance's default value is HIGH.
 * </p>
 *
 * @author Alexandru Stoica
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

    public List<T> getExercises() {
        return exercises;
    }

    public Integer getTotalScore() {
        return exercises.stream()
                .map(Exercise::getScore)
                .reduce(0, (acc, score) -> acc + score);
    }
}

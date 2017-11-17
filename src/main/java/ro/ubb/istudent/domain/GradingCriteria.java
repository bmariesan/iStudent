package ro.ubb.istudent.domain;

import java.util.List;

/**
 * <p>Describes how the final grade will be calculated.</p>
 * <p>
 * Each component is atomic, meaning for example you can find the the list of components
 * multiple partial exams of different or identical importance
 * </p>
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class GradingCriteria {

    private final List<Component> components;

    public GradingCriteria(final List<Component> components) {
        this.components = components;
    }

    /**
     * Returns atomic components that describe the
     * importance of each exam, assignment, tests in the final grade.
     *
     * @return - the list of components from the grading criteria
     */
    public List<Component> getComponents() {
        return components;
    }
}

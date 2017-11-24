package ro.ubb.istudent.domain;

import java.util.List;

/**
 * <p>Describes how the final grade will be calculated.</p>
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class GradingCriteria {

    private final List<Component> components;

    public GradingCriteria(final List<Component> components) {
        this.components = components;
    }

    public List<Component> getComponents() {
        return components;
    }
}

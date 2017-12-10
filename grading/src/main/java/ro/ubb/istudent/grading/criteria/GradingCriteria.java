package ro.ubb.istudent.grading.criteria;

import jdk.nashorn.internal.ir.annotations.Immutable;
import ro.ubb.istudent.grading.criteria.component.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Immutable
public class GradingCriteria {

    private final List<Component> components;

    public GradingCriteria(final List<Component> components) {
        this.components = components;
    }

    public List<Component> components() {
        return components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradingCriteria)) return false;
        GradingCriteria that = (GradingCriteria) o;
        return Objects.equals(components, that.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }
}

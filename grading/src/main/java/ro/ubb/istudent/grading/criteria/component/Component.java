package ro.ubb.istudent.grading.criteria.component;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.Objects;

import static ro.ubb.istudent.grading.criteria.component.ComponentImportance.HIGH;
import static ro.ubb.istudent.grading.criteria.component.ComponentType.FINAL_EXAM;

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

@Immutable
public class Component {

    private final ComponentType type;
    private final ComponentImportance importance;
    private final Double percent;

    public Component(final ComponentType type,
                     final ComponentImportance importance,
                     final Double percent) {
        this.type = type;
        this.importance = importance;
        this.percent = percent;
    }

    public Component(final ComponentType type, final Double percent) {
        this(type, HIGH, percent);
    }

    public Component() {
        this(FINAL_EXAM, HIGH, 100.0);
    }

    public ComponentType getType() {
        return type;
    }

    public ComponentImportance getImportance() {
        return importance;
    }

    public Double getPercent() {
        return percent;
    }

    /**
     * @param lowestImportancePercentage - the grading criteria's lowest importance percentage value.
     * @return - new component with adjusted percentage value.
     */
    public Component adjustPercentageWith(final Double lowestImportancePercentage) {
        return new Component(type, importance,
                normalizeValueOf(lowestImportancePercentage * importance.value() + percent));
    }

    /**
     * @return - rounds the value of the double if the floating point != 0.5
     * @apiNote - gets rid of the floating point representation problem.
     */
    private double normalizeValueOf(final double value) {
        return (value - (long) value == 0.5) ? value : (double) Math.round(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Component)) return false;
        Component component = (Component) o;
        return getType() == component.getType() &&
                getImportance() == component.getImportance() &&
                Objects.equals(getPercent(), component.getPercent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getImportance(), getPercent());
    }
}

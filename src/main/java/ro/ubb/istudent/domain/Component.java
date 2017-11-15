package ro.ubb.istudent.domain;

import static ro.ubb.istudent.domain.ComponentPriority.HIGH;

/**
 * <p>An object with represents a member of the grading criteria.</p>
 * <p>The object has a type (e.g FINAL_EXAM), priority (e.g OPTIONAL)
 * and a percent (how much the member matters when we calculate the final grade) </p>
 * <p>
 * The priority is used in order to adjust the percentage of the component.
 * If the grading criteria has a total percentage under 100.0%,
 * the components inside the grading criteria will be adjusted by our system.
 * </p>
 * <p>
 * The priority may be provided by the user, but by default the value of the priority is HIGH.
 * </p>
 * <p>
 * By using the priority the system allows the user to be flexible (the system will generate for
 * the end-user the right percentages for each component of the grading criteria).
 * </p>
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Component {

    private final ComponentType type;
    private final ComponentPriority priority;
    private final Double percent;

    public Component(final ComponentType type,
                     final ComponentPriority priority,
                     final Double percent) {
        this.type = type;
        this.priority = priority;
        this.percent = percent;
    }

    public Component(final ComponentType type, final Double percent) {
        this(type, HIGH, percent);
    }

    public ComponentType getType() {
        return type;
    }

    public ComponentPriority getPriority() {
        return priority;
    }

    public Double getPercent() {
        return percent;
    }

    /**
     * Returns a new adjusted component because the object is immutable.
     * The adjustment of the percentage is based on the component's priority (importance).
     * The function needs to know the percentage value of the LOW priority (as a base case)
     *
     * @param lowPriorityPercentage - the value of the LOW priority
     * @return - a new component with adjusted percentage value.
     */
    public Component adjustPercentageWith(final Double lowPriorityPercentage) {
        return new Component(type, priority,
                normalizeValueOf(lowPriorityPercentage * priority.getValue() + percent));
    }

    @Override
    public String toString() {
        return "Component(" + type + " - " + percent + ')';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Component)) return false;
        Component component = (Component) other;
        return getType() == component.getType() &&
                getPriority() == component.getPriority() &&
                getPercent().equals(component.getPercent());
    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + getPriority().hashCode();
        result = 31 * result + getPercent().hashCode();
        return result;
    }

    /**
     * Rounds the normalized value of the percentage.
     * <p> A percentage is normalized if the values is rounded to nearest whole part
     * or if the fractional value is equal to 0.5 </p>
     *
     * @param value - the not-normalized value
     * @return - the normalized value
     * @apiNote - this function gets rid of the floating point representation problem.
     */
    private double normalizeValueOf(final double value) {
        return (value - (long) value == 0.5) ? value : (double) Math.round(value);
    }
}

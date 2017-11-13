package ro.ubb.istudent.util;

import com.google.common.collect.ImmutableList;
import ro.ubb.istudent.domain.Component;
import ro.ubb.istudent.domain.GradingCriteria;
import ro.ubb.istudent.exception.GradingCriteriaPercentException;
import ro.ubb.istudent.exception.PercentOverflowException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Builds a grading criteria (GradingCriteria)</p>
 * <p>
 * The builder makes sure that the result criteria has the total percentage equal with 100.0%
 * </p>
 * <p>
 * If the given components have a total percentage over 100%, the builder will throw a PercentOverflowException.
 * </p>
 * <p>
 * If the given components have a total percentage under 100%,
 * the builder will adjust the value of the component's percentage to match the 100% limit.
 * </p>
 * <p>
 * The adjustment is based on the component's priority (see the docs from Component class for more details).
 * </p>
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class GradingCriteriaBuilder {

    private final List<Component> components;

    public GradingCriteriaBuilder() {
        this.components = ImmutableList.of();
    }

    private GradingCriteriaBuilder(final List<Component> components) {
        this.components = components;
    }

    /**
     * Adds the component to the grading criteria.
     *
     * @param component - the grading criteria's component
     * @return - the builder where component ∈ grading criteria
     */
    public GradingCriteriaBuilder addComponent(final Component component) {
        return new GradingCriteriaBuilder(ImmutableList.<Component>builder()
                .addAll(components).add(component).build());
    }

    /**
     * <p>Builds the grading criteria with the given data.</p>
     *
     * @return - the grading criteria
     * @throws PercentOverflowException - if the total percentage of the grading criteria > 100%.
     * @implNote This will adjust the value of the components' percentages if
     * the grading criteria's total percentage is under 100%!
     * @apiNote No matter the order of the components or the value of their percentages
     * this function will always return a grading criteria with a total percentage of 100%!
     */
    public GradingCriteria build() throws PercentOverflowException {
        if (Math.round(getUsedPercentage()) > 100.0)
            throw new PercentOverflowException(getUsedPercentage());
        return new GradingCriteria(getUsedPercentage() < 100.0 ?
                redistributePercentage() : components);
    }

    /**
     * <p>Builds the grading criteria with the given data without
     * redistribution (in case the total percentage < 100%)</p>
     *
     * @return The grading criteria
     * @throws GradingCriteriaPercentException if the grading's total percentage is different that 100%.
     * @apiNote This will NOT adjust the component's percentage but will force you to consider
     * the case when you have a total percentage under 100% by throwing GradingCriteriaPercentException!
     */
    public GradingCriteria buildWithoutRedistribution() throws GradingCriteriaPercentException {
        if (Math.round(getUsedPercentage()) != 100.0)
            throw new GradingCriteriaPercentException();
        return new GradingCriteria(components);
    }

    /**
     * Redistributes the component's percentage based on it's priority
     * and the value of the criteria's low priority percentage.
     * @return - the list of adjusted components
     */
    private List<Component> redistributePercentage() {
        return components.stream()
                .map(component -> component.adjustPercentageWith(getLowPriorityPercentage()))
                .collect(Collectors.toList());
    }

    /**
     * @return - the percentage addition value of a low priority component.
     * @implNote - reminderPercentage / sumOfPriorityValues
     * (e.g. 1 High + 2 Medium = 4 Low + 2 * 2 Low = 8 Low = 8 as a sum of priorities)
     */
    private Double getLowPriorityPercentage() {
        return (100.0 - getUsedPercentage()) / components.stream()
                .map(component -> component.getPriority().getValue())
                .reduce((acc, priority) -> acc + priority).orElse(0);
    }

    /**
     * @return - the percentage value of the current components
     */
    private Double getUsedPercentage() {
        return this.components.stream().map(Component::getPercent)
                .reduce((acc, item) -> acc + item).orElse(0.0);
    }
}

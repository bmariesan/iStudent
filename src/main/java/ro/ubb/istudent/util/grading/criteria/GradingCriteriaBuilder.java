package ro.ubb.istudent.util.grading.criteria;

import com.google.common.collect.ImmutableList;
import ro.ubb.istudent.domain.Component;
import ro.ubb.istudent.domain.GradingCriteria;
import ro.ubb.istudent.exception.PercentOverflowException;

import java.util.List;

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
    private final RedistributionStrategy strategy;

    public GradingCriteriaBuilder() {
        this(ImmutableList.of(), new WithRedistributionStrategy());
    }

    private GradingCriteriaBuilder(final List<Component> components,
                                   final RedistributionStrategy strategy) {
        this.components = components;
        this.strategy = strategy;
    }

    /**
     * <p>Useful if you want to add components to the builder using multiple threads.</p>
     * <p>The builder is immutable, so you can use it on multiple threads, in the end
     * get the components from the builder and concatenate the results</p>
     *
     * @return - the list of components from the builder
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * Adds the component to the grading criteria.
     *
     * @param component - the grading criteria's component
     * @return - the builder where component ∈ grading criteria
     */
    public GradingCriteriaBuilder addComponent(final Component component) {
        return new GradingCriteriaBuilder(ImmutableList.<Component>builder()
                .addAll(components).add(component).build(), this.strategy);
    }

    /**
     * This is the builder's default behaviour.
     *
     * @implNote This will adjust the value of the components' percentages if
     * the grading criteria's total percentage is under 100%!
     * @apiNote No matter the order of the components or the value of their percentages
     * this function will always return a grading criteria with a total percentage of 100%!
     */
    public GradingCriteriaBuilder withRedistribution() {
        return new GradingCriteriaBuilder(components, new WithRedistributionStrategy());
    }

    /**
     * @implNote This will NOT adjust the value of the components' percentages.
     */
    public GradingCriteriaBuilder withoutRedistribution() {
        return new GradingCriteriaBuilder(components, new NoRedistributionStrategy());
    }

    /**
     * Builds the grading criteria with the given data.
     *
     * @return - the grading criteria
     * @throws PercentOverflowException - if the total percentage of the grading criteria > 100% or
     *                                  if the total percentage of the grading criteria != 100 and the redistribution is deactivated.
     */
    public GradingCriteria build() throws PercentOverflowException {
        return new GradingCriteria(strategy.redistribute(getUsedPercentage(), components));
    }

    /**
     * @return - the percentage value of the current components
     */
    private Double getUsedPercentage() {
        return components.stream().map(Component::getPercent)
                .reduce((acc, item) -> acc + item).orElse(0.0);
    }
}

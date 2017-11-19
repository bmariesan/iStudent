package ro.ubb.istudent.util.grading.criteria;

import com.google.common.collect.ImmutableList;
import ro.ubb.istudent.domain.Component;
import ro.ubb.istudent.domain.GradingCriteria;
import ro.ubb.istudent.exception.PercentOverflowException;

import java.util.List;

/**
 * <p>Builds a grading criteria (GradingCriteria)</p>
 * <p>
 * The helper makes sure that the result criteria has the total percentage equal with 100.0%
 * If the given components have a total percentage over 100%, the builder will throw a PercentOverflowException.
 * If the given components have a total percentage under 100%,
 * the helper will adjust the value of the component's percentage to match the 100% limit.
 * </p>
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class GradingCriteriaBuilder {

    private List<Component> components;
    private RedistributionStrategy strategy;

    public GradingCriteriaBuilder() {
        components = ImmutableList.of();
        strategy = new WithRedistributionStrategy();
    }

    public GradingCriteriaBuilder addComponent(final Component component) {
        components = ImmutableList.<Component>builder()
                .addAll(components).add(component).build();
        return this;
    }

    public GradingCriteriaBuilder withRedistribution() {
        strategy = new WithRedistributionStrategy();
        return this;
    }

    public GradingCriteriaBuilder withoutRedistribution() {
        strategy = new NoRedistributionStrategy();
        return this;
    }

    /**
     * @throws PercentOverflowException - if the total percentage of the grading criteria > 100% or
     *                                  if the total percentage of the grading criteria != 100 and
     *                                  the redistribution is deactivated.
     */
    public GradingCriteria build() throws PercentOverflowException {
        return new GradingCriteria(strategy.redistribute(getUsedPercentage(), components));
    }

    private Double getUsedPercentage() {
        return components.stream().map(Component::getPercent)
                .reduce((acc, item) -> acc + item).orElse(0.0);
    }
}

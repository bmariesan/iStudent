package grading;

import com.google.common.collect.ImmutableList;
import domain.Component;
import domain.GradingCriteria;
import exception.PercentOverflowException;

import java.util.List;

/**
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

    public GradingCriteria build() throws PercentOverflowException {
        return new GradingCriteria(strategy.redistribute(getUsedPercentage(), components));
    }

    private Double getUsedPercentage() {
        return components.stream().map(Component::getPercent)
                .reduce((acc, item) -> acc + item).orElse(0.0);
    }
}

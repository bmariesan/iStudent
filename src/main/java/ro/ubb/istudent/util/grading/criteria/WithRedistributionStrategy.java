package ro.ubb.istudent.util.grading.criteria;

import ro.ubb.istudent.domain.Component;
import ro.ubb.istudent.exception.PercentOverflowException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class WithRedistributionStrategy implements RedistributionStrategy {

    @Override
    public List<Component> redistribute(final Double usedPercentage,
                                        final List<Component> components) throws PercentOverflowException {
        if (usedPercentage > 100) throw new PercentOverflowException();
        return usedPercentage < 100.0 ? redistributePercentage(usedPercentage, components) : components;
    }

    private List<Component> redistributePercentage(final Double usedPercentage, final List<Component> components) {
        return components.stream()
                .map(component -> component.adjustPercentageWith(getLowestImportancePercentage(usedPercentage, components)))
                .collect(Collectors.toList());
    }

    /**
     * @return - the percentage addition value of a low priority component.
     * @implNote - reminderPercentage / sumOfPriorityValues
     * (e.g. 1 High + 2 Medium = 4 Low + 2 * 2 Low = 8 Low = 8 as a sum of priorities)
     */
    private Double getLowestImportancePercentage(final Double usedPercentage, final List<Component> components) {
        return (100.0 - usedPercentage) / components.stream()
                .map(component -> component.getImportance().getValue())
                .reduce((acc, priority) -> acc + priority).orElse(0);
    }
}
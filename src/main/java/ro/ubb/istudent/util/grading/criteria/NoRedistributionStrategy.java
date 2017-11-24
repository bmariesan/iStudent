package ro.ubb.istudent.util.grading.criteria;

import ro.ubb.istudent.domain.Component;
import ro.ubb.istudent.exception.PercentOverflowException;

import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class NoRedistributionStrategy implements RedistributionStrategy {

    @Override
    public List<Component> redistribute(final Double usedPercentage,
                                        final List<Component> components) throws PercentOverflowException {
        if (usedPercentage != 100.0) {
            throw new PercentOverflowException();
        }
        return components;
    }
}
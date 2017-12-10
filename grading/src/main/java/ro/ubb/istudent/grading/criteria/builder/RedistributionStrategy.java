package ro.ubb.istudent.grading.criteria.builder;

import ro.ubb.istudent.grading.criteria.component.Component;
import ro.ubb.istudent.grading.exception.PercentOverflowException;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface RedistributionStrategy {
    List<Component> redistribute(final Double usedPercentage,
                                 final List<Component> components) throws PercentOverflowException;
}

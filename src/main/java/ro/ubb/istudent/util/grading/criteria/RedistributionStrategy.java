package ro.ubb.istudent.util.grading.criteria;

import ro.ubb.istudent.domain.Component;
import ro.ubb.istudent.exception.PercentOverflowException;

import java.util.List;


/**
 * Defines and encapsulates the redistribution algorithm;
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface RedistributionStrategy {

    List<Component> redistribute(final Double usedPercentage,
                                 final List<Component> components) throws PercentOverflowException;

}

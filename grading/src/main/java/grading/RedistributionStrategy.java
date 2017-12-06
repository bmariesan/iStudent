package grading;

import domain.Component;
import exception.PercentOverflowException;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface RedistributionStrategy {
    List<Component> redistribute(final Double usedPercentage,
                                 final List<Component> components) throws PercentOverflowException;
}

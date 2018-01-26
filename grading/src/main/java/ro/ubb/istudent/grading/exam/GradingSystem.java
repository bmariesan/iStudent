package ro.ubb.istudent.grading.exam;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface GradingSystem {
    Optional<Double> apply(
            List<CompletedUnitOfWork> unitsOfWork, GradingFormulaType type);
}

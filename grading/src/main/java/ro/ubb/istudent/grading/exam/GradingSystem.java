package ro.ubb.istudent.grading.exam;

import java.util.List;
import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

interface GradingSystem {
    Optional<Double> apply(
            List<CompletedUnitOfWork> unitsOfWork, GradingFormulaType type);
}

package ro.ubb.istudent.grading.exam;

import java.util.List;
import java.util.Optional;

public abstract class GradingFormula implements GradingSystem {

    private final GradingFormula next;
    private final GradingFormulaType type;

    public GradingFormula(
            final GradingFormula next,
            final GradingFormulaType type) {
        this.next = next;
        this.type = type;
    }

    GradingFormula(GradingFormulaType type) {
        this(null, type);
    }

    @Override
    public Optional<Double> apply(
            final List<CompletedUnitOfWork> unitsOfWork,
            final GradingFormulaType gradingFormulaType) {
        return this.type.equals(gradingFormulaType) ?
                Optional.of(this.calculate(unitsOfWork)) :
                tryToCalculateResultWithNextFormula(unitsOfWork, gradingFormulaType);
    }

    private Optional<Double> tryToCalculateResultWithNextFormula(
            final List<CompletedUnitOfWork> unitsOfWork,
            final GradingFormulaType withFormula) {
        return next != null ?
                next.apply(unitsOfWork, withFormula) :
                Optional.empty();
    }

    protected abstract Double calculate(List<CompletedUnitOfWork> unitsOfWork);
}

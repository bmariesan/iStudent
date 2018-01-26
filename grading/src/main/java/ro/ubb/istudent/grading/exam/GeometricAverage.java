package ro.ubb.istudent.grading.exam;

import java.util.List;

public class GeometricAverage extends GradingFormula {

    public GeometricAverage() {
        super(GradingFormulaType.GEOMETRIC_AVERAGE);
    }

    public GeometricAverage(GradingFormula next) {
        super(next, GradingFormulaType.GEOMETRIC_AVERAGE);
    }

    @Override
    protected Double calculate(List<CompletedUnitOfWork> unitsOfWork) {
        return Math.pow(getTheMultiplicationOfAllGrades(unitsOfWork), 1 / unitsOfWork.size());
    }

    private Double getTheMultiplicationOfAllGrades(List<CompletedUnitOfWork> unitsOfWork) {
        return unitsOfWork.stream()
                .mapToDouble(CompletedUnitOfWork::totalScore).boxed()
                .reduce(1.0, (it, acc) -> acc * it);
    }
}

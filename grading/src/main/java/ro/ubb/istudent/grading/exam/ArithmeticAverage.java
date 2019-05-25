package ro.ubb.istudent.grading.exam;

import java.util.List;
import java.util.function.Function;

public class ArithmeticAverage extends GradingFormula {

    private final Function<CompletedUnitOfWork, Double> getFinalGradeValueFromUnitOfWork =
            (unitOfWork) -> unitOfWork.totalScore() *
                    unitOfWork.gradingCriteriaComponent().percent() / 100;

    public ArithmeticAverage() {
        super(GradingFormulaType.ARITHMETIC_AVERAGE);
    }

    public ArithmeticAverage(GradingFormula next) {
        super(next, GradingFormulaType.ARITHMETIC_AVERAGE);
    }
    @Override
    protected Double calculate(List<CompletedUnitOfWork> unitsOfWork) {
        return unitsOfWork.stream()
                .mapToDouble(getFinalGradeValueFromUnitOfWork::apply)
                .sum();
    }
}

package ro.ubb.istudent.grading.exam;

import java.util.List;

public class HarmonicAverage extends GradingFormula {


    public HarmonicAverage() {
        super(GradingFormulaType.HARMONIC_AVERAGE);
    }

    public HarmonicAverage(GradingFormula next) {
        super(next, GradingFormulaType.HARMONIC_AVERAGE);
    }

    @Override
    protected Double calculate(List<CompletedUnitOfWork> unitsOfWork) {
        return unitsOfWork.size() / unitsOfWork.stream()
                .mapToDouble(it -> 1/it.totalScore()).sum();
    }
}

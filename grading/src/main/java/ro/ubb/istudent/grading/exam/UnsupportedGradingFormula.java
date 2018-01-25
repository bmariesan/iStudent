package ro.ubb.istudent.grading.exam;

public class UnsupportedGradingFormula extends RuntimeException {
    public UnsupportedGradingFormula() {
        super("Your grading formula is not supported yet!");
    }
}

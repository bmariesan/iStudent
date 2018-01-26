package ro.ubb.istudent.grading.exception;

class UnsupportedGradingFormula extends RuntimeException {
    public UnsupportedGradingFormula() {
        super("Your grading formula is not supported yet!");
    }
}

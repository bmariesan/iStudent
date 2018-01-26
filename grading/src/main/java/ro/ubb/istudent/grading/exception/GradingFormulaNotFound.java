package ro.ubb.istudent.grading.exception;

public class GradingFormulaNotFound extends RuntimeException {
    public GradingFormulaNotFound() {
        super("Unable to find the grading formula!");
    }
}

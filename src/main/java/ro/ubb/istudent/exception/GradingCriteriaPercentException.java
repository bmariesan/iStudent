package ro.ubb.istudent.exception;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class GradingCriteriaPercentException extends Exception {

    public GradingCriteriaPercentException() {
        super("The grading criteria has a total percentage != 100%");
    }
}

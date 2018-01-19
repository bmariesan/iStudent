package ro.ubb.istudent.grading.exception;

/**
 * @author Alexandru Stoica
 */

public class GradingCriteriaNotFound extends RuntimeException {
    public GradingCriteriaNotFound() {
        super("404 Grading Criteria Not Found!");
    }
}
package ro.ubb.istudent.exception;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class PercentOverflowException extends RuntimeException {

    public PercentOverflowException(final Double value) {
        super("The grading criteria's percentage of " + value + " is over the 100% limit");
    }
}

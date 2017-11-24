package ro.ubb.istudent.exception;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class PercentOverflowException extends RuntimeException {

    public PercentOverflowException() {
        super("The grading criteria's percentage is over the 100% limit");
    }
}

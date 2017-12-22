package ro.ubb.istudent.grading.criteria;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class PercentageOverflow extends RuntimeException {
    public PercentageOverflow() {
        super("The grading criteria's percentage is over the 100% limit");
    }
}

package ro.ubb.istudent.grading.exception;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

public class NoRightAnswer extends RuntimeException {
    public NoRightAnswer() {
        super("There are no right answers to wrong questions! :)");
    }
}

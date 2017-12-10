package ro.ubb.istudent.grading.exception;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super("404 Course Not Found!");
    }
}

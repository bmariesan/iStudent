package ro.ubb.istudent.grading.course;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class CourseNotFound extends RuntimeException {
    public CourseNotFound() {
        super("404 Course Not Found!");
    }
}
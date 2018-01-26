package ro.ubb.istudent.grading.exception;

public class GradingBookNotFound extends RuntimeException {
    public GradingBookNotFound() {
        super("404 Grading Book Not Found!");
    }
}
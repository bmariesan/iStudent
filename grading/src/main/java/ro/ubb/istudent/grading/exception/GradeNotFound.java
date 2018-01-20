package ro.ubb.istudent.grading.exception;

public class GradeNotFound extends RuntimeException {
    public GradeNotFound() {
        super("404 Grade Not Found!");
    }
}
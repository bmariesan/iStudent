package ro.ubb.istudent.grading.exception;

public class StudentNotFound extends RuntimeException {
    public StudentNotFound() {
        super("404 Student Not Found!");
    }
}
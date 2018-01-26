package ro.ubb.istudent.grading.exception;

public class GradingBookIsArchivedException extends RuntimeException {

    public GradingBookIsArchivedException() {
        super("The course's grading book is archived and you don't have the authority to view it.");
    }
}

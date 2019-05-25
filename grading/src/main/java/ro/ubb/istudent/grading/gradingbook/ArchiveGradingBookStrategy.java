package ro.ubb.istudent.grading.gradingbook;

import java.util.Calendar;
import java.util.Optional;

class ArchiveGradingBookStrategy {

    private final GradingBook gradingBook;

    public ArchiveGradingBookStrategy(final GradingBook gradingBook) {
        this.gradingBook = gradingBook;
    }

    Optional<GradingBook> archive() {
        return gradingBook.expiration().compareTo(Calendar.getInstance()) <= 0 ?
                Optional.empty() : Optional.of(gradingBook);
    }
}

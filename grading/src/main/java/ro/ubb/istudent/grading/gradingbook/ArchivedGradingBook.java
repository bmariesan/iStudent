package ro.ubb.istudent.grading.gradingbook;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import ro.ubb.istudent.grading.exception.GradingBookIsArchivedException;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ArchivedGradingBook extends GradingBookDecorator {
    public ArchivedGradingBook(GradingBook gradingBook) {
        super(new ArchiveGradingBookStrategy(gradingBook)
                .archive()
                .orElseThrow(GradingBookIsArchivedException::new));
    }
}

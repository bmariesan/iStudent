package ro.ubb.istudent.grading.gradingbook;


public class ArchivedGradingBook extends GradingBookDecorator {
    public ArchivedGradingBook(GradingBook gradingBook) {
        super(new ArchiveGradingBookStrategy(gradingBook)
                .archive()
                .orElseThrow(GradingBookIsArchivedException::new));
    }
}

package ro.ubb.istudent.grading.gradingbook;

import org.bson.types.ObjectId;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class GradingBookDecorator implements GradingBook {

    private final GradingBook gradingBook;

    public GradingBookDecorator(final GradingBook gradingBook) {
        this.gradingBook = gradingBook;
    }

    @Override
    public ObjectId id() {
        return gradingBook.id();
    }

    @Override
    public Calendar created() {
        return gradingBook.created();
    }

    @Override
    public Calendar expiration() {
        return gradingBook.expiration();
    }

    @Override
    public List<Grade> grades() {
        return gradingBook.grades();
    }

    @Override
    public GradingBook storeGrade(Grade grade) {
        return gradingBook.storeGrade(grade);
    }

    @Override
    public Optional<Grade> getGradeById(ObjectId id) {
        return gradingBook.getGradeById(id);
    }

    @Override
    public GradingBook deleteGrade(ObjectId gradeId) {
        return gradingBook.deleteGrade(gradeId);
    }
}

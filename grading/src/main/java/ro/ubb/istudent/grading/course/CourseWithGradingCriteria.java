package ro.ubb.istudent.grading.course;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.ubb.istudent.grading.criteria.GradingCriteria;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Immutable
@Document(collection = "course")
public class CourseWithGradingCriteria implements Course {

    private final ObjectId id;
    private final GradingCriteria criteria;

    public CourseWithGradingCriteria() {
        this(null, null);
    }

    public CourseWithGradingCriteria(final ObjectId id) {
        this(id, null);
    }

    public CourseWithGradingCriteria(
            final ObjectId id,
            final GradingCriteria gradingCriteria) {
        this.id = id;
        this.criteria = gradingCriteria;
    }

    @Override
    public ObjectId getId() {
        return id;
    }

    @Override
    public GradingCriteria gradingCriteria() {
        return criteria;
    }

    @Override
    public Course gradingCriteria(GradingCriteria gradingCriteria) {
        return new CourseWithGradingCriteria(id, gradingCriteria);
    }
}

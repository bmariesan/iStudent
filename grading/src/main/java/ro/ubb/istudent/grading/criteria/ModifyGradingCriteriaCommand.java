package ro.ubb.istudent.grading.criteria;

import javax.annotation.concurrent.Immutable;
import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.command.Command;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.course.CourseNotFound;
import ro.ubb.istudent.grading.course.CourseRepository;

/**
 * @author Alexandru Stoica
 */

@Immutable
public class ModifyGradingCriteriaCommand
        implements Command<Course> {

    private final CourseRepository repository;
    private final ObjectId courseId;
    private final GradingCriteria criteria;

    public ModifyGradingCriteriaCommand(
            final CourseRepository repository,
            final ObjectId courseId,
            final GradingCriteria criteria) {
        this.repository = repository;
        this.courseId = courseId;
        this.criteria = criteria;
    }

    @Override
    public Course execute() {
        return repository.save(findCourseAndUpdateIt());
    }

    private Course findCourseAndUpdateIt() {
        return repository.findById(courseId)
                .orElseThrow(CourseNotFound::new)
                .replaceGradingCriteriaWith(criteria);
    }
}

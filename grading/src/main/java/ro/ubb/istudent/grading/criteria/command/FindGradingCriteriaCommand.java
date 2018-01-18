package ro.ubb.istudent.grading.criteria.command;

import javax.annotation.concurrent.Immutable;
import lombok.ToString;
import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.command.Command;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.course.CourseNotFound;
import ro.ubb.istudent.grading.course.CourseRepository;
import ro.ubb.istudent.grading.criteria.exception.GradingCriteriaNotFound;
import ro.ubb.istudent.grading.criteria.domain.GradingCriteria;

@Immutable
@ToString(of = {"course"})
public class FindGradingCriteriaCommand
        implements Command<GradingCriteria> {

    private final CourseRepository repository;
    private final ObjectId course;

    public FindGradingCriteriaCommand(
            final CourseRepository repository,
            final ObjectId course) {
        this.repository = repository;
        this.course = course;
    }

    @Override
    public GradingCriteria execute() {
        return repository.findById(course)
                .orElseThrow(GradingCriteriaNotFound::new)
                .gradingCriteria();
    }

    /**
     * @author Alexandru Stoica
     */

    @Immutable
    public static class ModifyGradingCriteriaCommand
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
}

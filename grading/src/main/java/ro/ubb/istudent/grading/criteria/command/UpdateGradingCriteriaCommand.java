package ro.ubb.istudent.grading.criteria.command;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.command.Command;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.course.CourseRepository;
import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.exception.CourseNotFoundException;

@Immutable
public class UpdateGradingCriteriaCommand implements Command<Course> {

    private final CourseRepository repository;
    private final ObjectId course;
    private final GradingCriteria criteria;

    public UpdateGradingCriteriaCommand(
            final CourseRepository repository,
            final ObjectId course,
            final GradingCriteria criteria) {
        this.repository = repository;
        this.course = course;
        this.criteria = criteria;
    }

    @Override
    public Course execute() {
        return repository.save(findCourseAndUpdateIt());
    }

    private Course findCourseAndUpdateIt() {
        return repository.findById(course)
                .orElseThrow(CourseNotFoundException::new)
                .gradingCriteria(criteria);
    }
}

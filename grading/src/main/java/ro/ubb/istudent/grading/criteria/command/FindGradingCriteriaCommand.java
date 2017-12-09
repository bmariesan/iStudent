package ro.ubb.istudent.grading.criteria.command;

import ro.ubb.istudent.grading.criteria.GradingCriteria;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.command.Command;
import ro.ubb.istudent.grading.course.CourseRepository;
import ro.ubb.istudent.grading.exception.CourseNotFoundException;

@Immutable
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
                .orElseThrow(CourseNotFoundException::new)
                .gradingCriteria();
    }
}

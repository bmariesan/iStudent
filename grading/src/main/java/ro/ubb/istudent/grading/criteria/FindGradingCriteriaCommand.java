package ro.ubb.istudent.grading.criteria;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.ToString;
import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.command.Command;
import ro.ubb.istudent.grading.course.CourseRepository;

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
}

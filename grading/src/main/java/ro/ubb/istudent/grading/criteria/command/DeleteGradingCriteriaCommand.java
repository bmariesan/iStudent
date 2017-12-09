package ro.ubb.istudent.grading.criteria.command;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.command.Command;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.course.CourseRepository;

@Immutable
public class DeleteGradingCriteriaCommand implements Command<Course> {

    private final UpdateGradingCriteriaCommand command;

    public DeleteGradingCriteriaCommand(
            final CourseRepository repository,
            final ObjectId course) {
        command = new UpdateGradingCriteriaCommand(repository, course, null);
    }

    @Override
    public Course execute() {
        return command.execute();
    }
}

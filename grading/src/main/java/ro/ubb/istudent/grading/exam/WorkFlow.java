package ro.ubb.istudent.grading.exam;

import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ro.ubb.istudent.grading.gradingbook.User;

import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@ToString
@EqualsAndHashCode(of={"fromStudent", "unitsOfWork"})
public class WorkFlow {

    private final List<CompletedUnitOfWork> unitsOfWork;
    private final User student;

    public WorkFlow(
            final List<CompletedUnitOfWork> unitsOfWork,
            final User student) {
        this.unitsOfWork = unitsOfWork;
        this.student = student;
    }

    public User student() {
        return student;
    }

    public List<CompletedUnitOfWork> unitsOfWork() {
        return unitsOfWork;
    }

    public WorkFlow addUnitOfWork(CompletedUnitOfWork unitOfWork) {
        return new WorkFlow(ImmutableList.<CompletedUnitOfWork>builder()
                .add(unitOfWork).addAll(unitsOfWork).build(), student);
    }
}

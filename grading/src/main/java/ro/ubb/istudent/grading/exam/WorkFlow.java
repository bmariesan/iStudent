package ro.ubb.istudent.grading.exam;

import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.criteria.GradingCriteriaComponent;
import ro.ubb.istudent.grading.gradingbook.Grade;
import ro.ubb.istudent.grading.gradingbook.NormalGrade;
import ro.ubb.istudent.grading.gradingbook.User;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@ToString
@EqualsAndHashCode(of={"student", "unitsOfWork"})
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

    public Optional<Grade> grade(final GradingCriteria gradingCriteria) {
        return isGradingCriteriaMeet(gradingCriteria) ?
                calculateFinalGradeForWorkFlow() :
                Optional.empty();
    }

    private Optional<Grade> calculateFinalGradeForWorkFlow() {
        Double finalGradeValue = unitsOfWork.stream().mapToDouble(
                it -> it.totalScore() * it.gradingCriteriaComponent().percent() / 100)
                .sum();
        // TODO: Round By Two Digits.
        return Optional.of(new NormalGrade(ObjectId.get(), finalGradeValue, student));
    }

    private Boolean isGradingCriteriaMeet(final GradingCriteria gradingCriteria) {
        List<GradingCriteriaComponent> typesFromWorkUnits =
                unitsOfWork.stream().map(UnitOfWork::gradingCriteriaComponent)
                        .collect(Collectors.toList());
        return new HashSet<>(gradingCriteria.components())
                .equals(new HashSet<>(typesFromWorkUnits));
    }
}

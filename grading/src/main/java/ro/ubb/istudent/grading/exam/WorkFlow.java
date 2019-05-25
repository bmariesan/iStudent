package ro.ubb.istudent.grading.exam;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.criteria.GradingCriteriaComponent;
import ro.ubb.istudent.grading.exception.GradingFormulaNotFound;
import ro.ubb.istudent.grading.gradingbook.Grade;
import ro.ubb.istudent.grading.gradingbook.SolidGrade;
import ro.ubb.istudent.grading.gradingbook.User;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode(of = {"student", "unitsOfWork"})
public class WorkFlow implements Serializable {


    @JsonProperty
    private final List<CompletedUnitOfWork> unitsOfWork;

    @JsonProperty
    private final User student;

    @SuppressWarnings("unused")
    public WorkFlow() {
        this(new ArrayList<>(), null);
    }

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

    public Optional<Grade> grade(
            final GradingCriteria gradingCriteria, final GradingFormulaType type) {
        return isGradingCriteriaMeet(gradingCriteria) ?
                calculateFinalGradeForWorkFlowWithFormulaType(type) :
                Optional.empty();
    }

    public Optional<Grade> calculateFinalGradeForWorkFlowWithFormulaType(
            final GradingFormulaType type) {
        Double finalGradeValue = new ArithmeticAverage(new HarmonicAverage(new GeometricAverage()))
                .apply(unitsOfWork, type)
                .orElseThrow(GradingFormulaNotFound::new);
        Double roundedFinalGradeValue = Double.valueOf(
                new DecimalFormat("#.##").format(finalGradeValue));
        return Optional.of(new SolidGrade(
                ObjectId.get(), roundedFinalGradeValue, student));
    }

    private Boolean isGradingCriteriaMeet(final GradingCriteria gradingCriteria) {
        List<GradingCriteriaComponent> typesFromWorkUnits =
                unitsOfWork.stream().map(UnitOfWork::gradingCriteriaComponent)
                        .collect(Collectors.toList());
        return new HashSet<>(gradingCriteria.components())
                .equals(new HashSet<>(typesFromWorkUnits));
    }
}

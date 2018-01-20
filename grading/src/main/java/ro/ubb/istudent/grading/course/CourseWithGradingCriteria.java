package ro.ubb.istudent.grading.course;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.concurrent.Immutable;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.exam.CompletedUnitOfWork;
import ro.ubb.istudent.grading.gradingbook.GradingBook;
import ro.ubb.istudent.grading.gradingbook.User;
import ro.ubb.istudent.grading.exam.WorkFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Immutable
@Document(collection = "course")
public class CourseWithGradingCriteria implements Course {

    @JsonProperty
    private final ObjectId id;

    @JsonProperty
    private final GradingCriteria criteria;

    @JsonProperty
    private final List<WorkFlow> workFlows;

    @JsonProperty
    private final GradingBook gradingBook;

    @JsonProperty
    private final User teacher;

    public CourseWithGradingCriteria() {
        this(ObjectId.get(), null, new ArrayList<>(), null, null);
    }

    public CourseWithGradingCriteria(final ObjectId id) {
        this(id, null, new ArrayList<>(), null, null);
    }

    public CourseWithGradingCriteria(
            final ObjectId id,
            final GradingCriteria gradingCriteria) {
        this(id, gradingCriteria, new ArrayList<>(), null, null);
    }

    public CourseWithGradingCriteria(
            final ObjectId id,
            final GradingCriteria gradingCriteria,
            final List<WorkFlow> workFlows,
            final GradingBook gradingBook,
            final User teacher) {
        this.id = id;
        this.criteria = gradingCriteria;
        this.workFlows = workFlows;
        this.gradingBook = gradingBook;
        this.teacher = teacher;
    }

    public CourseWithGradingCriteria(
            final ObjectId id,
            final GradingCriteria gradingCriteria,
            final List<WorkFlow> workFlows,
            final User teacher) {
        this(id, gradingCriteria, workFlows, null, teacher);
    }

    @Override
    public ObjectId getId() {
        return id;
    }

    @Override
    public Optional<GradingBook> gradingBook() {
        return Optional.ofNullable(gradingBook);
    }

    @Override
    public List<WorkFlow> workFlows() {
        return workFlows;
    }

    @Override
    public Optional<GradingCriteria> gradingCriteria() {
        return Optional.ofNullable(criteria);
    }

    @Override
    public Optional<User> teacher() {
        return Optional.ofNullable(teacher);
    }

    @Override
    public Course replaceGradingCriteriaWith(GradingCriteria gradingCriteria) {
        return new CourseWithGradingCriteria(id, gradingCriteria);
    }

    @Override
    public Optional<WorkFlow> getWorkFlowForStudent(User student) {
        return workFlows.stream().filter(it -> it.student()
                .equals(student)).findFirst();
    }

    private WorkFlow getForCreateWorkFlowForWorkUnit(CompletedUnitOfWork unitOfWork) {
        return getWorkFlowForStudent(unitOfWork.student()).orElse(
                new WorkFlow(emptyList(), unitOfWork.student()))
                .addUnitOfWork(unitOfWork);
    }

    @Override
    public Course addUnitOfWork(final CompletedUnitOfWork unitOfWork) {
        return new CourseWithGradingCriteria(id, criteria,
                addOrReplaceWorkflow(getForCreateWorkFlowForWorkUnit(unitOfWork)),
                gradingBook, teacher);
    }

    private List<WorkFlow> addOrReplaceWorkflow(WorkFlow workFlow) {
        workFlows.add(workFlow);
        workFlows.replaceAll(it -> it.equals(workFlow) ? workFlow : it);
        return workFlows;
    }


}

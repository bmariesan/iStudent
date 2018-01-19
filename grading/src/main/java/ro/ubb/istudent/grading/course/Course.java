package ro.ubb.istudent.grading.course;

import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.exam.CompletedUnitOfWork;
import ro.ubb.istudent.grading.gradingbook.GradingBook;
import ro.ubb.istudent.grading.gradingbook.User;
import ro.ubb.istudent.grading.exam.WorkFlow;

import java.util.List;
import java.util.Optional;

public interface Course {
    ObjectId getId();
    List<WorkFlow> workFlows();
    Optional<GradingBook> gradingBook();
    Optional<GradingCriteria> gradingCriteria();
    Optional<User> teacher();
    Optional<WorkFlow> getWorkFlowForStudent(User student);
    Course replaceGradingCriteriaWith(GradingCriteria gradingCriteria);
    Course addUnitOfWork(CompletedUnitOfWork unitOfWork);
}

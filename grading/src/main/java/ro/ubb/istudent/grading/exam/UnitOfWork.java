package ro.ubb.istudent.grading.exam;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.criteria.GradingCriteriaComponent;
import ro.ubb.istudent.grading.criteria.GradingCriteriaComponentType;
import ro.ubb.istudent.grading.gradingbook.Teacher;
import ro.ubb.istudent.grading.gradingbook.User;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "exam")
@JsonSubTypes({@JsonSubTypes.Type(
        value = UnitOfWorkWithCompletedExercises.class,
        name = "completed-exam")})
public interface UnitOfWork extends Serializable {
    ObjectId id();
    GradingCriteriaComponent gradingCriteriaComponent();
}


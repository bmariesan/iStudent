package ro.ubb.istudent.grading.exam;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.criteria.GradingCriteriaComponent;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "unit-of-work")
@JsonSubTypes({@JsonSubTypes.Type(
        value = CompletedUnitOfWork.class,
        name = "unit-of-work")})
public interface UnitOfWork extends Serializable {
    ObjectId id();
    GradingCriteriaComponent gradingCriteriaComponent();
}


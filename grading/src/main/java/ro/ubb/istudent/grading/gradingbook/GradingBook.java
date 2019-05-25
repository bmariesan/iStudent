package ro.ubb.istudent.grading.gradingbook;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "grading-book")
@JsonSubTypes({@JsonSubTypes.Type(
        value = SolidGradingBook.class,
        name = "@grading-book")})
public interface GradingBook extends Serializable {
    ObjectId id();
    Calendar created();
    Calendar expiration();
    List<Grade> grades();
    GradingBook storeGrade(Grade grade);
    Optional<Grade> getGradeById(ObjectId id);
    GradingBook deleteGrade(ObjectId gradeId);
    GradingBook changeExpirationDate(Calendar yesterday);
}

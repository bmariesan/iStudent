package ro.ubb.istudent.grading.gradingbook;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface GradingBook extends Serializable {
    ObjectId id();
    Calendar created();
    Calendar expiration();
    List<Grade> grades();
    GradingBook storeGrade(Grade grade);
    Optional<Grade> getGradeById(ObjectId id);
    GradingBook deleteGrade(ObjectId gradeId);
}

package ro.ubb.istudent.grading.course;

import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.criteria.domain.GradingCriteria;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Course {
    ObjectId getId();
    GradingCriteria gradingCriteria();
    Course replaceGradingCriteriaWith(GradingCriteria gradingCriteria);
}

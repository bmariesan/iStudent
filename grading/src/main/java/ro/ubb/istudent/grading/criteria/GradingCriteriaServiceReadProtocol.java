package ro.ubb.istudent.grading.criteria;

import org.bson.types.ObjectId;

/**
 * @author Alexandru Stoica
 */

public interface GradingCriteriaServiceReadProtocol {
    GradingCriteria getByCourseId(ObjectId course);
}

package ro.ubb.istudent.grading.criteria.service;

import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.criteria.domain.GradingCriteria;

/**
 * @author Alexandru Stoica
 */

public interface GradingCriteriaServiceReadProtocol {
    GradingCriteria getByCourseId(ObjectId course);
}

package ro.ubb.istudent.grading.criteria.service;

import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.criteria.GradingCriteria;

public interface GradingCriteriaServiceWriteProtocol {
    Course insert(GradingCriteria criteria, ObjectId course);
}

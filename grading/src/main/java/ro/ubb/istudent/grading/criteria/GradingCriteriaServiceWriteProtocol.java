package ro.ubb.istudent.grading.criteria;

import org.bson.types.ObjectId;
import ro.ubb.istudent.grading.course.Course;

/**
 * @author Alexandru Stoica
 */

public interface GradingCriteriaServiceWriteProtocol {

    Course insert(GradingCriteria criteria, ObjectId courseId);

    Course insertWithComponentsRedistribution(
            GradingCriteria criteria, ObjectId courseId);

    Course delete(ObjectId courseId);

    Course update(GradingCriteria criteria, ObjectId courseId);
}
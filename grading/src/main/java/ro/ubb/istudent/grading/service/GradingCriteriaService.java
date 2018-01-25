package ro.ubb.istudent.grading.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.criteria.GradingCriteriaWithValidatedPercentage;
import ro.ubb.istudent.grading.criteria.RedistributedGradingCriteria;
import ro.ubb.istudent.grading.exception.CourseNotFound;
import ro.ubb.istudent.grading.repository.CourseRepository;

/**
 * Created by Marius on 10.12.2017.
 */

@Service
public class GradingCriteriaService {

    private final CourseRepository courseRepository;

    @Autowired
    public GradingCriteriaService(final CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course saveGradingCriteriaToCourse(
            final GradingCriteria gradingCriteria, final ObjectId courseId) {
        return courseRepository.save(getCourseFromDatabaseAndReplaceGradingCriteria(
               new GradingCriteriaWithValidatedPercentage(gradingCriteria), courseId));
    }

    public Course saveGradingCriteriaWithRedistribution(
            final GradingCriteria gradingCriteria, final ObjectId courseId) {
        return courseRepository.save(getCourseFromDatabaseAndReplaceGradingCriteria(
                new RedistributedGradingCriteria(gradingCriteria), courseId));
    }

    public Course deleteGradingCriteriaFromCourse(final ObjectId courseId) {
        return courseRepository.save(getCourseFromDatabaseAndReplaceGradingCriteria(
                null, courseId));
    }

    private Course getCourseFromDatabaseAndReplaceGradingCriteria(
            final GradingCriteria gradingCriteria, final ObjectId courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(CourseNotFound::new)
                .replaceGradingCriteriaWith(gradingCriteria);
    }
}

package ro.ubb.istudent.grading.criteria.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.course.CourseRepository;
import ro.ubb.istudent.grading.criteria.GradingCriteria;


@Service
public class GradingCriteriaService implements
        GradingCriteriaServiceReadProtocol,
        GradingCriteriaServiceWriteProtocol {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course insert(GradingCriteria criteria, ObjectId course) {
        return null;
    }
}

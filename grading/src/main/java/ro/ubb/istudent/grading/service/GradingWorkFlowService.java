package ro.ubb.istudent.grading.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.exam.CompletedUnitOfWork;
import ro.ubb.istudent.grading.exception.CourseNotFound;
import ro.ubb.istudent.grading.exception.GradingCriteriaNotFound;
import ro.ubb.istudent.grading.exception.StudentNotFound;
import ro.ubb.istudent.grading.gradingbook.Grade;
import ro.ubb.istudent.grading.repository.CourseRepository;
import ro.ubb.istudent.grading.gradingbook.SolidGrade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GradingWorkFlowService {

    private final CourseRepository courseRepository;

    @Autowired
    public GradingWorkFlowService(final CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addUnitOfWorkFromStudent(
            final CompletedUnitOfWork unitOfWork, final ObjectId courseId) {
        return courseRepository.save(getCourseById(courseId).addUnitOfWork(unitOfWork));
    }

    public Grade gradeUnitOfWork(final CompletedUnitOfWork unitOfWork) {
        return new SolidGrade(ObjectId.get(), unitOfWork.totalScore(),
                unitOfWork.fromStudent().orElseThrow(StudentNotFound::new));
    }

    public List<Grade> gradeCourseForEachStudent(final ObjectId courseId) {
        Course course = getCourseById(courseId);
        return course.workFlows().stream()
                .map(it -> it.grade(course.gradingCriteria()
                        .orElseThrow(GradingCriteriaNotFound::new)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Course getCourseById(final ObjectId courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(CourseNotFound::new);
    }
}

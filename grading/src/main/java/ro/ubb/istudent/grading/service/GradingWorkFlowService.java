package ro.ubb.istudent.grading.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.exam.CompletedUnitOfWork;
import ro.ubb.istudent.grading.exception.CourseNotFound;
import ro.ubb.istudent.grading.exception.StudentNotFound;
import ro.ubb.istudent.grading.gradingbook.Grade;
import ro.ubb.istudent.grading.repository.CourseRepository;
import ro.ubb.istudent.grading.repository.GradeRepository;
import ro.ubb.istudent.grading.gradingbook.NormalGrade;

@Service
public class GradingWorkFlowService {

    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public GradingWorkFlowService(
            final GradeRepository gradeRepository,
            final CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
    }

    public Course addUnitOfWorkFromStudent(
            final CompletedUnitOfWork unitOfWork, final ObjectId courseId) {
        return courseRepository.save(getCourseById(courseId).addUnitOfWork(unitOfWork));
    }

    private Course getCourseById(final ObjectId courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(CourseNotFound::new);
    }

    public Grade gradeUnitOfWork(CompletedUnitOfWork unitOfWork) {
        return new NormalGrade(ObjectId.get(), unitOfWork.totalScore(),
                unitOfWork.fromStudent().orElseThrow(StudentNotFound::new));
    }
}

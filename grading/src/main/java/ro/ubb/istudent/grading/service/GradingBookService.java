package ro.ubb.istudent.grading.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.exception.CourseNotFound;
import ro.ubb.istudent.grading.exception.GradeNotFound;
import ro.ubb.istudent.grading.exception.GradingBookNotFound;
import ro.ubb.istudent.grading.gradingbook.*;
import ro.ubb.istudent.grading.repository.CourseRepository;

/**
 * Created by Marius on 10.12.2017.
 */

@Service
public class GradingBookService {

    private final CourseRepository courseRepository;

    @Autowired
    public GradingBookService(
            final CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course storeGradeInGradingBook(final Grade grade, final ObjectId courseId) {
        return saveGradingBookToCourse(courseId, getCourseById(courseId).gradingBook()
                .orElseThrow(GradingBookNotFound::new).storeGrade(grade));
    }

    public Course deleteGradeFromGradingBook(final ObjectId gradeId, final ObjectId courseId) {
        GradingBook gradingBook = getCourseById(courseId)
                .gradingBook().orElseThrow(GradingBookNotFound::new);
        return saveGradingBookToCourse(courseId, gradingBook.deleteGrade(gradeId));
    }

    private Course getCourseById(final ObjectId courseId) {
        return courseRepository.findById(courseId).orElseThrow(CourseNotFound::new);
    }

    public Course saveGradingBookToCourse(ObjectId courseId, GradingBook gradingBook) {
        return courseRepository.save(getCourseById(courseId)
                .replaceGradingBookWith(gradingBook));
    }

    public Course deleteGradingBookFromCourse(ObjectId courseId) {
        return saveGradingBookToCourse(courseId, null);
    }

    public GradingBook getGradingBookFromCourse(
            final ObjectId courseId, final User authenticatedPrincipal) {
        GradingBook gradingBook = getCourseById(courseId).gradingBook()
                .orElseThrow(GradeNotFound::new);
        return authenticatedPrincipal.role().equals(UserRole.STUDENT) ?
                new ArchivedGradingBook(gradingBook) : gradingBook;
    }
} 

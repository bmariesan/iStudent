package ro.ubb.istudent.grading.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.exception.CourseNotFound;
import ro.ubb.istudent.grading.exception.GradeNotFound;
import ro.ubb.istudent.grading.exception.GradingBookNotFound;
import ro.ubb.istudent.grading.gradingbook.Grade;
import ro.ubb.istudent.grading.gradingbook.GradingBook;
import ro.ubb.istudent.grading.repository.CourseRepository;
import ro.ubb.istudent.grading.repository.GradeRepository;

/**
 * Created by Marius on 10.12.2017.
 */

@Service
public class GradingBookService {

    private final CourseRepository courseRepository;
    private final GradeRepository gradeRepository;

    @Autowired
    public GradingBookService(
            final CourseRepository courseRepository,
            final GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.gradeRepository = gradeRepository;
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
} 

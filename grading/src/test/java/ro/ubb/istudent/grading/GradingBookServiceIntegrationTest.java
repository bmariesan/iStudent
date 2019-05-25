package ro.ubb.istudent.grading;

import org.bson.types.ObjectId;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.course.CourseWithGradingCriteria;
import ro.ubb.istudent.grading.exception.GradingBookIsArchivedException;
import ro.ubb.istudent.grading.exception.GradingBookNotFound;
import ro.ubb.istudent.grading.gradingbook.*;
import ro.ubb.istudent.grading.repository.CourseRepository;
import ro.ubb.istudent.grading.service.GradingBookService;
import ro.ubb.istudent.grading.service.GradingCriteriaService;

import java.util.Calendar;

import static java.util.Collections.singletonList;
import static java.util.Collections.sort;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@DataMongoTest
@ExtendWith(SpringExtension.class)
@DisplayName("Grading Criteria Integration Tests")
@SpringBootTest(classes = {GradingCriteriaService.class})
@EnableMongoRepositories("ro.ubb.istudent.grading")
@TestPropertySource(properties = "spring.data.mongodb.embedded.port=0")
@ComponentScan(basePackages = {"ro.ubb.istudent.grading"})
class GradingBookServiceIntegrationTest {

    @Autowired
    private GradingBookService gradingBookService;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void whenTeacherAddsGradingBookToCourse_ExpectGradingBookAddedToCourse() {
        // given:
        GradingBook gradingBook = new SolidGradingBook(singletonList(
                new SolidGrade(10.0, new Student("Student"))));
        Course course = courseRepository.save(new CourseWithGradingCriteria());
        // when:
        Course courseFromDatabase = gradingBookService
                .saveGradingBookToCourse(course.getId(), gradingBook);
        // then:
        assertThat(courseFromDatabase.gradingBook()
                .orElseThrow(GradingBookNotFound::new), Is.is(gradingBook));
    }

    @Test
    void whenTeacherDeletesGradingBookFromCourse_ExpectGradingBookDeleted() {
        // given:
        GradingBook gradingBook = new SolidGradingBook(singletonList(
                new SolidGrade(10.0, new Student("Student"))));
        Course course = courseRepository.save(new CourseWithGradingCriteria()
                .replaceGradingBookWith(gradingBook));
        // when:
        Course courseFromDatabase = gradingBookService
                .deleteGradingBookFromCourse(course.getId());
        // then:
        assertFalse(courseFromDatabase.gradingBook().isPresent());
    }

    @Test
    void whenTeacherAddsGradeToGradingBook_ExpectGradeAddedToGradingBook() {
        // given:
        GradingBook gradingBook = new SolidGradingBook();
        Course course = courseRepository.save(new CourseWithGradingCriteria()
                .replaceGradingBookWith(gradingBook));
        Grade grade = new SolidGrade(10.0, new Student("Student"));
        // when:
        GradingBook gradingBookFromDatabase = gradingBookService
                .storeGradeInGradingBook(grade, course.getId())
                .gradingBook()
                .orElseThrow(GradingBookNotFound::new);

        // then:
        assertThat(gradingBookFromDatabase.grades().get(0), Is.is(grade));
    }

    @Test
    void whenTeacherDeletesGradeFromGradingBook_ExpectGradeDeletedFromGradingBook() {
        // given:
        GradingBook gradingBook = new SolidGradingBook(singletonList(
                new SolidGrade(10.0, new Student("Student"))));
        Course course = courseRepository.save(new CourseWithGradingCriteria()
                .replaceGradingBookWith(gradingBook));
        Grade grade = course.gradingBook()
                .orElseThrow(GradingBookNotFound::new).grades().get(0);
        // when:
        GradingBook gradingBookFromDatabase = gradingBookService
                .deleteGradeFromGradingBook(grade.id(), course.getId())
                .gradingBook().orElseThrow(GradingBookNotFound::new);
        // then:
        assertThat(gradingBookFromDatabase.grades().size(), Is.is(0));
    }

    @Test
    void whenTeacherGetsGradingBook_ExpectGradingBook() {
        whenGettingGradingBook_withPrinciple(new Teacher("Test"));
    }

    @Test
    void whenStudentGetsGradingBook_GradingBookAvailable_ExpectGradingBook() {
        whenGettingGradingBook_withPrinciple(new Student("Student"));
    }

    @Test
    void whenStudentGetsGradingBook_AfterExpirationDate_ExpectGradingBookArchivedException() {
        // given:
        Calendar yesterday = Calendar.getInstance();
        yesterday.roll(Calendar.DAY_OF_WEEK, -1);
        GradingBook gradingBook = new SolidGradingBook(ObjectId.get(),
                singletonList(new SolidGrade(10.0, new Student("Student"))), yesterday);
        User student = new Student("Student");
        Course course = courseRepository.save(new CourseWithGradingCriteria()
                .replaceGradingBookWith(gradingBook));
        // when && then:
        assertThrows(GradingBookIsArchivedException.class, () ->
                gradingBookService.getGradingBookFromCourse(course.getId(), student));
    }

    private void whenGettingGradingBook_withPrinciple(final User user) {
        // given:
        GradingBook gradingBook = new SolidGradingBook(singletonList(
                new SolidGrade(10.0, new Student("Student"))));
        Course course = courseRepository.save(new CourseWithGradingCriteria()
                .replaceGradingBookWith(gradingBook));
        // when:
        GradingBook gradingBookFromDatabase = gradingBookService
                .getGradingBookFromCourse(course.getId(), user);
        // then:
        assertThat(gradingBookFromDatabase.id(), Is.is(gradingBook.id()));
    }
}
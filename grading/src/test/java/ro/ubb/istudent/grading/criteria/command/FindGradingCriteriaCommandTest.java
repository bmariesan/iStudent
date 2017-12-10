package ro.ubb.istudent.grading.criteria.command;

import org.bson.types.ObjectId;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.course.CourseRepository;
import ro.ubb.istudent.grading.course.CourseWithGradingCriteria;
import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.criteria.component.Component;
import ro.ubb.istudent.grading.exception.CourseNotFoundException;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@DataMongoTest
@ExtendWith(SpringExtension.class)
@DisplayName("Find Grading Criteria Command Test")
@SpringBootTest(classes = {CourseRepository.class})
@EnableMongoRepositories("ro.ubb.istudent.grading.course")
@TestPropertySource(properties = "spring.data.mongodb.embedded.port=0")
class FindGradingCriteriaCommandTest {

    @Autowired
    private CourseRepository repository;

    @Test
    void whenSearchingCriteria_CourseExists_ExpectGradingCriteria() {
        // preconditions:
        GradingCriteria gradingCriteria =
                new GradingCriteria(singletonList(new Component()));
        Course course = repository.save(
                new CourseWithGradingCriteria().gradingCriteria(gradingCriteria));
        // when:
        GradingCriteria actual = new FindGradingCriteriaCommand(
                repository, course.getId()).execute();
        // then:
        MatcherAssert.assertThat(actual, is(equalTo(course.gradingCriteria())));
    }

    @Test
    void whenSearchingCriteria_CourseNotFound_ExpectNotFoundException() {
        // preconditions:
        GradingCriteria gradingCriteria =
                new GradingCriteria(singletonList(new Component()));
        Course course = repository.save(
                new CourseWithGradingCriteria().gradingCriteria(gradingCriteria));
        // then:
        assertThrows(CourseNotFoundException.class,
                () -> new FindGradingCriteriaCommand(
                        repository, new ObjectId()).execute());
    }
}

package ro.ubb.istudent.grading.criteria.command;

import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.criteria.component.Component;
import org.bson.types.ObjectId;
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
import ro.ubb.istudent.grading.criteria.component.ComponentImportance;
import ro.ubb.istudent.grading.criteria.component.ComponentType;
import ro.ubb.istudent.grading.exception.CourseNotFoundException;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@DataMongoTest
@ExtendWith(SpringExtension.class)
@DisplayName("Update Grading Criteria Command Test")
@SpringBootTest(classes = {CourseRepository.class})
@EnableMongoRepositories("ro.ubb.istudent.grading.course")
@TestPropertySource(properties = "spring.data.mongodb.embedded.port=0")
class UpdateGradingCriteriaCommandTest {

    @Autowired
    private CourseRepository repository;

    @Test
    void whenUpdatingCriteria_CourseExists_ExpectCourseUpdated() {
        // preconditions:
        GradingCriteria gradingCriteria =
                new GradingCriteria(singletonList(new Component()));
        GradingCriteria updatedGradingCriteria =
                new GradingCriteria(singletonList(new Component(ComponentType.PARTIAL_EXAM, ComponentImportance.LOW, 10.0)));
        Course course = repository.save(
                new CourseWithGradingCriteria().gradingCriteria(gradingCriteria));
        // when:
        Course actual = new UpdateGradingCriteriaCommand(
                repository, course.getId(), updatedGradingCriteria).execute();
        // then:
        assertThat(actual.gradingCriteria(), is(equalTo(updatedGradingCriteria)));
    }

    @Test
    void whenInsertingCriteria_CourseExists_ExpectCriteriaInserted() {
        // preconditions:
        GradingCriteria gradingCriteria = new GradingCriteria(singletonList(new Component()));
        Course course = repository.save(new CourseWithGradingCriteria());
        // when:
        Course actual = new UpdateGradingCriteriaCommand(
                repository, course.getId(), gradingCriteria).execute();
        // then:
        assertThat(actual.gradingCriteria(), is(equalTo(gradingCriteria)));
    }

    @Test
    void whenInsertingCriteria_CourseNotFound_ExpectNotFoundException() {
        // preconditions:
        GradingCriteria gradingCriteria =
                new GradingCriteria(singletonList(new Component()));
        // then:
        assertThrows(CourseNotFoundException.class,
                () -> new UpdateGradingCriteriaCommand(
                        repository, new ObjectId(), gradingCriteria).execute());
    }
}

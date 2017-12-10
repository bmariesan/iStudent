package ro.ubb.istudent.grading.criteria.command;

import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.criteria.component.Component;
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

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@DataMongoTest
@ExtendWith(SpringExtension.class)
@DisplayName("Delete Grading Criteria Command Test")
@SpringBootTest(classes = {CourseRepository.class})
@EnableMongoRepositories("ro.ubb.istudent.grading.course")
@TestPropertySource(properties = "spring.data.mongodb.embedded.port=0")
class DeleteGradingCriteriaCommandTest {

    @Autowired
    private CourseRepository repository;

    @Test
    void whenDeletingCriteria_CourseExists_ExpectCriteriaDeleted() {
        // preconditions:
        GradingCriteria gradingCriteria =
                new GradingCriteria(singletonList(new Component()));
        Course course = repository.save(
                new CourseWithGradingCriteria().gradingCriteria(gradingCriteria));
        // when:
        new DeleteGradingCriteriaCommand(repository, course.getId()).execute();
        GradingCriteria actual = repository.findOne(course.getId()).gradingCriteria();
        // then:
        assertThat(actual, nullValue());
    }
}

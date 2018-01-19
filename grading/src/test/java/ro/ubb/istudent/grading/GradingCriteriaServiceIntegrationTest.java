package ro.ubb.istudent.grading;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
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
import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.criteria.GradingCriteriaComponent;
import ro.ubb.istudent.grading.criteria.GradingCriteriaComponentImportance;
import ro.ubb.istudent.grading.criteria.GradingCriteriaComponentType;
import ro.ubb.istudent.grading.exception.GradingCriteriaNotFound;
import ro.ubb.istudent.grading.exception.PercentageOverflow;
import ro.ubb.istudent.grading.repository.CourseRepository;
import ro.ubb.istudent.grading.repository.TeacherRepository;
import ro.ubb.istudent.grading.service.GradingCriteriaService;

import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
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
class GradingCriteriaServiceIntegrationTest {

    @Autowired
    private GradingCriteriaService gradingCriteriaService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    private final Supplier<GradingCriteria> gradingCriteriaSupplier = () -> new GradingCriteria(asList(
            new GradingCriteriaComponent(GradingCriteriaComponentType.FINAL_EXAM,
                    GradingCriteriaComponentImportance.HIGH, 50.0),
            new GradingCriteriaComponent(GradingCriteriaComponentType.PARTIAL_EXAM,
                    GradingCriteriaComponentImportance.MEDIUM, 50.0),
            new GradingCriteriaComponent(GradingCriteriaComponentType.ASSIGNMENT,
                    GradingCriteriaComponentImportance.OPTIONAL, 0.0)));

    @Test
    void whenTeacherInsertsGradingCriteriaIntoCourse_ExpectGradingCriteriaAddedToCourse() {
        // given:
        GradingCriteria expected = gradingCriteriaSupplier.get();
        Course course = courseRepository.save(new CourseWithGradingCriteria());
        // when:
        Course courseFromDatabase = gradingCriteriaService
                .saveGradingCriteriaToCourse(expected, course.getId());
        // then:
        assertThat(courseFromDatabase.gradingCriteria()
                        .orElseThrow(GradingCriteriaNotFound::new)
                        .components(), containsInAnyOrder(expected.components().toArray()));
    }

    @Test
    void whenTeacherSavesInvalidGradingCriteriaIntoCourse_ExpectGradingCriteriaException() {
        // given:
        GradingCriteria expected = new GradingCriteria(singletonList(
                new GradingCriteriaComponent(GradingCriteriaComponentType.PARTIAL_EXAM,
                        GradingCriteriaComponentImportance.MEDIUM, 10.0)));
        Course course = courseRepository.save(new CourseWithGradingCriteria());
        // when && then:
        assertThrows(PercentageOverflow.class, () -> gradingCriteriaService
                .saveGradingCriteriaToCourse(expected, course.getId()));
    }

    @Test
    void whenTeacherSavesWithRedistributionGradingCriteriaIntoCourse_ExpectGradingCriteriaInserted() {
        // given:
        GradingCriteria expected = new GradingCriteria(singletonList(
                new GradingCriteriaComponent(GradingCriteriaComponentType.PARTIAL_EXAM,
                        GradingCriteriaComponentImportance.MEDIUM, 10.0)));
        Course course = courseRepository.save(new CourseWithGradingCriteria());
        // when:
        Course courseFromDatabase = gradingCriteriaService
                .saveGradingCriteriaWithRidistribution(expected, course.getId());
        // then:
        assertThat(courseFromDatabase.gradingCriteria()
                .orElseThrow(GradingCriteriaNotFound::new)
                .totalPercentage(), Is.is(100.0));
    }

    @Test
    void whenTeacherDeletesGradingCriteriaFromCourse_ExpectGradingCriteriaDeletedFromCourse() {
        // given:
        GradingCriteria expected = gradingCriteriaSupplier.get();
        Course course = courseRepository.save(new CourseWithGradingCriteria()
                .replaceGradingCriteriaWith(expected));
        // when:
        Course courseFromDatabase = gradingCriteriaService
                .deleteGradingCriteriaFromCourse(course.getId());
        // then:
        assertThat(courseFromDatabase.gradingCriteria().isPresent(), Is.is(false));
    }

    @Test
    void whenTeacherUpdatesGradingCriteriaFromCourse_ExpectGradingCriteriaUpdated() {
        // given:
        GradingCriteria gradingCriteria = gradingCriteriaSupplier.get();
        Course course = courseRepository.save(new CourseWithGradingCriteria())
                .replaceGradingCriteriaWith(gradingCriteria);
        GradingCriteria expected = gradingCriteria.addGradingCriteriaComponent(
                new GradingCriteriaComponent(GradingCriteriaComponentType.ASSIGNMENT,
                        GradingCriteriaComponentImportance.OPTIONAL, 0.0));
        // when:
        Course courseFromDatabase = gradingCriteriaService
                .saveGradingCriteriaToCourse(expected, course.getId());
        // then:
        assertThat(courseFromDatabase.gradingCriteria()
                .orElseThrow(GradingCriteriaNotFound::new)
                .components(), containsInAnyOrder(expected.components().toArray()));
    }
}
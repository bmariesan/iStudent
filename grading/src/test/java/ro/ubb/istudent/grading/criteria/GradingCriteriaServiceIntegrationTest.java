package ro.ubb.istudent.grading.criteria;

import org.hamcrest.core.IsNull;
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
import ro.ubb.istudent.grading.course.CourseNotFound;
import ro.ubb.istudent.grading.course.CourseRepository;
import ro.ubb.istudent.grading.course.CourseWithGradingCriteria;
import ro.ubb.istudent.grading.criteria.domain.GradingCriteria;
import ro.ubb.istudent.grading.criteria.domain.GradingCriteriaComponent;
import ro.ubb.istudent.grading.criteria.domain.GradingCriteriaComponentImportance;
import ro.ubb.istudent.grading.criteria.domain.GradingCriteriaComponentType;
import ro.ubb.istudent.grading.criteria.exception.GradingCriteriaNotFound;
import ro.ubb.istudent.grading.criteria.exception.PercentageOverflow;
import ro.ubb.istudent.grading.criteria.service.GradingCriteriaService;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@DataMongoTest
@ExtendWith(SpringExtension.class)
@DisplayName("Grading Criteria Integration Tests")
@SpringBootTest(classes = {GradingCriteriaService.class})
@EnableMongoRepositories("ro.ubb.istudent.grading.course")
@TestPropertySource(properties = "spring.data.mongodb.embedded.port=0")
@ComponentScan(basePackages = {"ro.ubb.istudent.grading.criteria"})
class GradingCriteriaServiceIntegrationTest {

    @Autowired
    private GradingCriteriaService gradingCriteriaService;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void whenInsertingGradingCriteria_CourseExists_ExpectGradingCriteriaInserted() {
        // given:
        GradingCriteria gradingCriteria = new
                GradingCriteria(singletonList(new GradingCriteriaComponent()));
        Course course = courseRepository.save(new CourseWithGradingCriteria());
        // when:
        Course actual = gradingCriteriaService
                .insert(gradingCriteria, course.getId());
        // then:
        assertThat(actual.gradingCriteria().components(),
                contains(gradingCriteria.components().toArray()));
    }

    @Test
    void whenInsertingGradingCriteriaWithRedistribution_CourseExists_ExpectGradingCriteriaWith100Percent() {
        // given:
        GradingCriteria gradingCriteria = new
                GradingCriteria(singletonList(new
                GradingCriteriaComponent(GradingCriteriaComponentType.FINAL_EXAM,
                GradingCriteriaComponentImportance.HIGH, 10.0)));
        Course course = courseRepository.save(new CourseWithGradingCriteria());
        // when:
        Course actual = gradingCriteriaService
                .insertWithComponentsRedistribution(gradingCriteria, course.getId());
        // then:
        assertThat(actual.gradingCriteria().totalPercentage(),
                is(equalTo(100.0)));
    }

    @Test
    void whenInsertingGradingCriteriaWithPercentageOver100_ExpectPercentageOverflow() {
        // given:
        GradingCriteria gradingCriteria = new
                GradingCriteria(singletonList(new
                GradingCriteriaComponent(GradingCriteriaComponentType.FINAL_EXAM,
                GradingCriteriaComponentImportance.HIGH, 110.0)));
        Course course = courseRepository.save(new CourseWithGradingCriteria());
        // when && then:
        assertThrows(PercentageOverflow.class, () -> gradingCriteriaService
                .insert(gradingCriteria, course.getId()));
    }

    @Test
    void whenDeletingGradingCriteria_CourseExists_ExpectGradingCriteriaDeleted() {
        // given:
        GradingCriteria gradingCriteria = new
                GradingCriteria(singletonList(new GradingCriteriaComponent()));
        Course course = courseRepository.save(new CourseWithGradingCriteria()
                .replaceGradingCriteriaWith(gradingCriteria));
        // when:
        Course actual = gradingCriteriaService.delete(course.getId());
        // then:
        assertThat(actual.gradingCriteria(), IsNull.nullValue());
    }

    @Test
    void whenUpdatingGradingCriteria_CourseExists_ExpectGradingCriteriaUpdated() {
        // given:
        GradingCriteria gradingCriteria = new GradingCriteria(singletonList(
                new GradingCriteriaComponent()));
        GradingCriteria updated = new GradingCriteria(singletonList(
                new GradingCriteriaComponent(GradingCriteriaComponentType.ASSIGNMENT,
                        GradingCriteriaComponentImportance.LOW, 0.0)));
        Course course = courseRepository.save(new CourseWithGradingCriteria()
                .replaceGradingCriteriaWith(gradingCriteria));
        // when:
        Course actual = gradingCriteriaService.update(updated, course.getId());
        // then:
        assertThat(actual.gradingCriteria().components(),
                contains(updated.components().toArray()));
    }

    @Test
    void whenChangingGradingCriteria_CourseNotFound_ExpectCourseNotFound() {
        // given:
        GradingCriteria gradingCriteria = new
                GradingCriteria(singletonList(new GradingCriteriaComponent()));
        Course course = new CourseWithGradingCriteria();
        // when && then:
        assertThrows(CourseNotFound.class,
                () -> gradingCriteriaService.insert(gradingCriteria, course.getId()));
    }

    @Test
    void whenSearchingForGradingCriteria_GradingCriteriaExists_ExpectGradingCriteria() {
        // given:
        GradingCriteria gradingCriteria = new GradingCriteria(singletonList(
                new GradingCriteriaComponent()));
        Course course = courseRepository.save(new CourseWithGradingCriteria()
                .replaceGradingCriteriaWith(gradingCriteria));
        // when:
        GradingCriteria actual = gradingCriteriaService.getByCourseId(course.getId());
        // then:
        assertThat(actual, is(gradingCriteria));
    }

    @Test
    void whenSearchingForGradingCriteria_GradingCriteriaNotFound_ExpectGradingCriteriaNotFound() {
        // given:
        Course course = new CourseWithGradingCriteria();
        // when && then:
        assertThrows(GradingCriteriaNotFound.class,
                () -> gradingCriteriaService.getByCourseId(course.getId()));
    }
}
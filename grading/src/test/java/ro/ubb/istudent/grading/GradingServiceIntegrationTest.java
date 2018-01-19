package ro.ubb.istudent.grading;

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
@EnableMongoRepositories("ro.ubb.istudent.grading")
@TestPropertySource(properties = "spring.data.mongodb.embedded.port=0")
@ComponentScan(basePackages = {"ro.ubb.istudent.grading"})
class GradingServiceIntegrationTest {
    // TODO
}
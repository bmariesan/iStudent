package ro.ubb.istudent.grading.criteria.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.ubb.istudent.grading.course.CourseRepository;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@ExtendWith(SpringExtension.class)
@EnableMongoRepositories("ro.ubb.istudent.grading.course")
@SpringBootTest(classes = {GradingCriteriaService.class, CourseRepository.class})
class GradingCriteriaServiceTest {

    @Autowired
    private GradingCriteriaService gradingCriteriaService;

    @MockBean
    private CourseRepository courseRepositoryGradingService;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void whenCourseExists_expectGradingCriteriaInserted() { }
}
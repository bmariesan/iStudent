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
import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.criteria.GradingCriteriaComponent;
import ro.ubb.istudent.grading.criteria.GradingCriteriaComponentImportance;
import ro.ubb.istudent.grading.criteria.GradingCriteriaComponentType;
import ro.ubb.istudent.grading.exam.*;
import ro.ubb.istudent.grading.exception.StudentNotFound;
import ro.ubb.istudent.grading.gradingbook.Grade;
import ro.ubb.istudent.grading.gradingbook.Student;
import ro.ubb.istudent.grading.gradingbook.Teacher;
import ro.ubb.istudent.grading.gradingbook.User;
import ro.ubb.istudent.grading.repository.CourseRepository;
import ro.ubb.istudent.grading.service.GradingWorkFlowService;
import ro.ubb.istudent.grading.exam.WorkFlow;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static java.util.Arrays.sort;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@DataMongoTest
@ExtendWith(SpringExtension.class)
@DisplayName("Grading WorkFlow Integration Tests")
@SpringBootTest(classes = {GradingWorkFlowService.class})
@EnableMongoRepositories("ro.ubb.istudent.grading")
@TestPropertySource(properties = "spring.data.mongodb.embedded.port=0")
@ComponentScan(basePackages = {"ro.ubb.istudent.grading"})
class GradingWorkFlowServiceIntegrationTest {

    @Autowired
    private GradingWorkFlowService workFlowService;

    @Autowired
    private CourseRepository courseRepository;

    private final Supplier<Question> questionSupplier = () ->
            new ChoiceQuestion("Text", singletonList("good"),
                    asList("not good", "good"), 50.0);

    @Test
    void whenTeachersAddsAnUnitOfWorkForStudent_ExpectUnitOfWorkAddedToStudent() {
        // given:
        List<Exercise> exercises = asList(
                new CompletedExercise(questionSupplier.get(), singletonList("good")),
                new CompletedExercise(questionSupplier.get(), singletonList("not good")));
        User student = new Student("Student");
        CompletedUnitOfWork unitOfWork =
                new UnitOfWorkWithCompletedExercises(exercises, student);
        Course course = courseRepository.save(new CourseWithGradingCriteria());
        // when:
        Course courseFromDatabase = workFlowService
                .addUnitOfWorkFromStudent(unitOfWork, course.getId());
        WorkFlow actual = courseFromDatabase
                .getWorkFlowForStudent(student).orElseThrow(StudentNotFound::new);
        // then:
        assertThat(actual.unitsOfWork().get(0), Is.is(unitOfWork));
    }

    @Test
    void whenTeachersGradesAUnitOfWorkForStudent_ExpectUnitOfWorkGraded() {
        // given:
        List<Exercise> exercises = asList(
                new CompletedExercise(questionSupplier.get(), singletonList("good")),
                new CompletedExercise(questionSupplier.get(), singletonList("not good")));
        User student = new Student("Student");
        CompletedUnitOfWork unitOfWork =
                new UnitOfWorkWithCompletedExercises(exercises, student);
        // when:
        Grade grade = workFlowService.gradeUnitOfWork(unitOfWork);
        // then:
        assertThat(grade.value(), Is.is(50.0));
    }

    @Test
    void whenTeacherGradesAWorkflowForStudent_ExpectGradeSavedInDatabase() {
        // given:
        GradingCriteriaComponent finalExamGradingComponent =
                new GradingCriteriaComponent(GradingCriteriaComponentType.FINAL_EXAM,
                        GradingCriteriaComponentImportance.HIGH, 60.0);
        GradingCriteriaComponent partialExamGradingComponent =
                new GradingCriteriaComponent(GradingCriteriaComponentType.PARTIAL_EXAM,
                        GradingCriteriaComponentImportance.MEDIUM, 40.0);

        GradingCriteria gradingCriteria = new GradingCriteria(
                asList(finalExamGradingComponent, partialExamGradingComponent));

        List<Exercise> exercises = asList(
                new CompletedExercise(questionSupplier.get(), singletonList("good")),
                new CompletedExercise(questionSupplier.get(), singletonList("not good")));

        User student = new Student("Student");
        User teacher = new Teacher("Teacher");

        CompletedUnitOfWork finalExam = new UnitOfWorkWithCompletedExercises(
                exercises, student, finalExamGradingComponent);
        CompletedUnitOfWork partialExam = new UnitOfWorkWithCompletedExercises(
                exercises, student, partialExamGradingComponent);

        WorkFlow workFlow = new WorkFlow(asList(finalExam, partialExam), student);
        Course course = courseRepository.save(new CourseWithGradingCriteria(
                ObjectId.get(), gradingCriteria, singletonList(workFlow), teacher));
        // when:
        List<Grade> grades = workFlowService.gradeCourseForEachStudent(course.getId());
        // then:
        assertThat(grades.get(0).value(), Is.is(50.0));
    }
}
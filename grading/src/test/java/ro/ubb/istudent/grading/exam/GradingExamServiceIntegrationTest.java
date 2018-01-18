package ro.ubb.istudent.grading.exam;

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
import ro.ubb.istudent.grading.course.CourseRepository;
import ro.ubb.istudent.grading.criteria.service.GradingCriteriaService;
import ro.ubb.istudent.grading.exam.controller.GradingExamController;
import ro.ubb.istudent.grading.exam.domain.*;
import ro.ubb.istudent.grading.exam.service.GradeRepository;
import ro.ubb.istudent.grading.exam.service.GradingExamService;
import ro.ubb.istudent.grading.gradingbook.domain.*;
import ro.ubb.istudent.grading.gradingbook.repository.StudentRepository;
import ro.ubb.istudent.grading.gradingbook.repository.TeacherRepository;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
@ExtendWith(SpringExtension.class)
@DisplayName("Grading Exam Integration Tests")
@SpringBootTest(classes = {GradingExamService.class})
@EnableMongoRepositories("ro.ubb.istudent.grading")
@TestPropertySource(properties = "spring.data.mongodb.embedded.port=0")
@ComponentScan(basePackages = {"ro.ubb.istudent.grading"})
public class GradingExamServiceIntegrationTest {

    @Autowired
    private GradingExamService gradingExamService;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    private Question makeQuestion() {
        List<String> rightAnswers = singletonList("is good");
        List<String> possibleAnswers = asList("is good", "is not good");
        return new ChoiceQuestion("Text", rightAnswers, possibleAnswers, 10.0);
    }

    @Test
    void whenGradingExam_WithValidExam_ExpectCorrectGrade() {
        // given:
        List<CompletedExercise> exercises = asList(
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is not good")),
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is good")));
        NormalStudent student = studentRepository
                .save(new NormalStudent("Student"));
        NormalTeacher teacher = teacherRepository
                .save(new NormalTeacher("Teacher"));
        CompletedExam exam = new ExamWithCompletedExercises(
                ObjectId.get(), exercises, student, teacher);
        // when:
        Grade actualGrade = gradingExamService.grade(exam);
        // then:
        assertThat(gradeRepository.findAll().contains(actualGrade), Is.is(true));

    }
}
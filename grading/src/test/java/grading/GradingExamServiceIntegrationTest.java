package grading;

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
import ro.ubb.istudent.grading.exam.domain.*;
import ro.ubb.istudent.grading.exam.service.GradingExamService;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@DataMongoTest
@ExtendWith(SpringExtension.class)
@DisplayName("Grading Exam Integration Tests")
@SpringBootTest(classes = {GradingExamService.class})
@EnableMongoRepositories("ro.ubb.istudent.grading.exam")
@TestPropertySource(properties = "spring.data.mongodb.embedded.port=0")
@ComponentScan(basePackages = {"ro.ubb.istudent.grading.exam"})
class GradingExamServiceIntegrationTest {

    @Autowired
    private GradingExamService gradingExamService;

    private Question makeQuestion() {
        List<String> rightAnswers = singletonList("is good");
        List<String> possibleAnswers = asList("is good", "is not good");
        return new ChoiceQuestion("Text", rightAnswers, possibleAnswers, 10.0);
    }

    @Test
    void whenInsertingGradingCriteria_CourseExists_ExpectGradingCriteriaInserted() {
        // given:
        List<Exercise> exercises = asList(
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is not good")),
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is good")));
        Exam exam = new Exam(ObjectId.get(), exercises);
        // when:
        Double score = gradingExamService.getTotalScoreFromExam(exam);
        // then:
        assertThat(score, Is.is(score));
    }

    @Test
    void whenAskingForRightAnswers_WithValidExam_ExpectRightAnswersFromExam() {
        // given:
        List<Exercise> exercises = asList(
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is not good")),
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is good")));
        Exam exam = new Exam(ObjectId.get(), exercises);
        List<String> rightAnswersFromUser = singletonList("is good");
        List<String> allRightAnswersFromExam = asList("is good", "is good");
        // when:
        List<Answer> answers = gradingExamService.getRightAnswersFromExam(exam);
        List<String> actualRightAnswersFromUser = answers.stream()
                .flatMap(it -> it.rightAnswersFromUserInExercise().stream())
                .collect(Collectors.toList());
        List<String> actualAllRightAnswersFromExam = answers.stream()
                .flatMap(it -> it.allRightAnswersFromExercise().stream())
                .collect(Collectors.toList());
        System.out.println(actualAllRightAnswersFromExam);
        // then:
        assertAll(() -> assertThat(actualRightAnswersFromUser,
                containsInAnyOrder(rightAnswersFromUser.toArray())),
                () -> assertThat(actualAllRightAnswersFromExam,
                        containsInAnyOrder(allRightAnswersFromExam.toArray())));
    }
}
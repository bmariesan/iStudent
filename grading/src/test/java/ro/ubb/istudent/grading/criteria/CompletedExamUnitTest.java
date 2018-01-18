package ro.ubb.istudent.grading.criteria;

import org.bson.types.ObjectId;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ro.ubb.istudent.grading.exam.domain.*;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@DisplayName("Completed Exam Unit Tests")
class CompletedExamUnitTest {

    private Question makeQuestion() {
        List<String> rightAnswers = singletonList("is good");
        List<String> possibleAnswers = asList("is good", "is not good");
        return new ChoiceQuestion("Text", rightAnswers, possibleAnswers, 10.0);
    }

    @Test
    void whenGradingExam_ExamCompletedByUser_ExpectCorrectScore() {
        // given:
        List<Exercise> exercises = asList(
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is not good")),
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is good")));
        CompletedExam exam = new ExamWithCompletedExercises(ObjectId.get(), exercises);
        // when:
        Double score = exam.totalScore();
        // then:
        assertThat(score, Is.is(score));
    }

    @Test
    void whenAskingForCorrectExercises_WithValidExam_ExpectCorrectExercisesFromExam() {
        // given:
        List<Exercise> exercises = asList(
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is not good")),
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is good")));
        CompletedExam exam = new ExamWithCompletedExercises(ObjectId.get(), exercises);
        List<Exercise> expectedCorrectExercisesFromUser = exercises.subList(1, 2);
        // when:
        List<Exercise> actualCorrectExercisesFromUser = exam.correctExercises();
        // then:
        assertThat(actualCorrectExercisesFromUser,
                containsInAnyOrder(expectedCorrectExercisesFromUser.toArray()));
    }

    @Test
    void whenAskingForPartiallyCorrectExercises_WithValidExam_ExpectPartiallyCorrectExercisesFromExam() {
        // given:
        List<Exercise> exercises = asList(
                new CompletedExercise(ObjectId.get(), makeQuestion(), singletonList("is not good")),
                new CompletedExercise(ObjectId.get(), makeQuestion(), asList("is good", "is not good")));
        CompletedExam exam = new ExamWithCompletedExercises(ObjectId.get(), exercises);
        List<Exercise> expectedPartiallyCorrectExercisesFromUser = exercises.subList(1, 2);
        // when:
        List<Exercise> actualPartiallyCorrectExercisesFromUser =
                exam.partiallyCorrectExercises();
        actualPartiallyCorrectExercisesFromUser.forEach(System.out::println);
        // then:
        assertThat(actualPartiallyCorrectExercisesFromUser,
                containsInAnyOrder(expectedPartiallyCorrectExercisesFromUser.toArray()));
    }
}
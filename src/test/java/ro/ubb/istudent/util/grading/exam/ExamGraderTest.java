package ro.ubb.istudent.util.grading.exam;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ro.ubb.istudent.domain.*;
import ro.ubb.istudent.exception.NoRightAnswer;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

class ExamGraderTest {

    private <T> Exam<Exercise> provideExamBasedOn(Question<T> choiceQuestion, T answer) {
        return new Exam<>(singletonList(new CompletedExercise<>(choiceQuestion, answer)));
    }

    @Test
    @DisplayName("Is Grading Text Exams?")
    void isGradingTextExams() {
        TextQuestion textQuestion = new TextQuestion("Unit-Tests?", 100.0);
        Exam<Exercise> exam = provideExamBasedOn(textQuestion, null);
        // when:
        NoRightAnswer actual =
                assertThrows(NoRightAnswer.class, exam::getTotalScore);
        // then:
        assertThat(actual.getMessage()).contains("There are no right answers to wrong questions");
    }

    @Test
    @DisplayName("Is Grading Multiple Choice Exams?")
    void isGradingMultipleChoiceExams() {
        // declaration:
        ChoiceQuestion<List<String>> choiceQuestion = new ChoiceQuestionBuilder<List<String>>()
                .withText("Unit-Tests?")
                .withPossibleAnswers(asList("yes", "obviously", "no"))
                .withRightAnswer(asList("yes", "obviously"))
                .build();
        Exam<Exercise> exam = provideExamBasedOn(choiceQuestion, asList("yes", "obviously"));
        // then:
        assertEquals((double)exam.getTotalScore(), 100.0);
    }

    @Test
    @DisplayName("Is Grading Single Choice Exams?")
    void isGradingSingleChoiceExams() {
        // declaration:
        ChoiceQuestion<String> choiceQuestion = new ChoiceQuestionBuilder<String>()
                .withText("Unit-Tests?")
                .withPossibleAnswers(asList("yes", "no"))
                .withRightAnswer("yes")
                .build();
        Exam<Exercise> exam = provideExamBasedOn(choiceQuestion, "yes");
        // then:
        assertEquals((double)exam.getTotalScore(), 100.0);
    }
}
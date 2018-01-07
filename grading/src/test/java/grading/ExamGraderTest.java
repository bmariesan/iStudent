package grading;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ro.ubb.istudent.grading.domain.*;
import ro.ubb.istudent.grading.exam.ChoiceQuestionBuilder;
import ro.ubb.istudent.grading.exception.NoRightAnswer;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

class ExamGraderTest {

    private Exam provideExamBasedOn(Question choiceQuestion, String answer) {
        return new Exam(singletonList(new CompletedExercise(choiceQuestion, answer)));
    }

    @Test
    @DisplayName("Is Grading Text Exams?")
    void isGradingTextExams() {
        TextQuestion textQuestion = new TextQuestion("Unit-Tests?", 100.0);
        Exam exam = provideExamBasedOn(textQuestion, null);
        // when:
        NoRightAnswer actual =
                Assertions.assertThrows(NoRightAnswer.class, exam::getTotalScore);
        // then:
        assertThat(actual.getMessage(), containsString("There are no right answers to wrong questions"));
    }

    @Test
    @DisplayName("Is Grading Multiple Choice Exams?")
    void isGradingMultipleChoiceExams() {
        // declaration:
        ChoiceQuestion choiceQuestion = new ChoiceQuestionBuilder()
                .withText("Unit-Tests?")
                .withPossibleAnswers(asList("yes", "obviously", "no"))
                .withRightAnswer("yes")
                .build();
        Exam exam = provideExamBasedOn(choiceQuestion, "yes");
        // then:
        Assertions.assertEquals((double)exam.getTotalScore(), 100.0);
    }

    @Test
    @DisplayName("Is Grading Single Choice Exams?")
    void isGradingSingleChoiceExams() {
        // declaration:
        ChoiceQuestion choiceQuestion = new ChoiceQuestionBuilder()
                .withText("Unit-Tests?")
                .withPossibleAnswers(asList("yes", "no"))
                .withRightAnswer("yes")
                .build();
        Exam exam = provideExamBasedOn(choiceQuestion, "yes");
        // then:
        Assertions.assertEquals((double)exam.getTotalScore(), 100.0);
    }
}
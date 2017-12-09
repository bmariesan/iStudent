package grading;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ro.ubb.istudent.grading.criteria.GradingCriteria;
import ro.ubb.istudent.grading.criteria.builder.GradingCriteriaBuilder;
import ro.ubb.istudent.grading.criteria.component.Component;
import ro.ubb.istudent.grading.exception.PercentOverflowException;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static ro.ubb.istudent.grading.criteria.component.ComponentImportance.HIGH;
import static ro.ubb.istudent.grading.criteria.component.ComponentImportance.LOW;
import static ro.ubb.istudent.grading.criteria.component.ComponentImportance.MEDIUM;
import static ro.ubb.istudent.grading.criteria.component.ComponentType.ASSIGNMENT;
import static ro.ubb.istudent.grading.criteria.component.ComponentType.FINAL_EXAM;
import static ro.ubb.istudent.grading.criteria.component.ComponentType.PARTIAL_EXAM;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@DisplayName("Grading Criteria Builder Unit Tests")
class GradingCriteriaBuilderTest {

    private Function<Integer, Double> generatePercentage =
            (bound) -> (double) new Random().nextInt(bound);

    @Test
    @DisplayName("Building Grading Criteria Trivial")
    void isBuildingTrivialCriteria() {
        // when:
        GradingCriteria actual = new GradingCriteriaBuilder()
                .addComponent(new Component(FINAL_EXAM, 50.0))
                .addComponent(new Component(PARTIAL_EXAM, 25.0))
                .addComponent(new Component(PARTIAL_EXAM, 25.0))
                .build();
        // then:
        Assertions.assertEquals(100.0, (double) getTotalPercentageFromCriteria(actual));
    }

    @Test
    @DisplayName("Building Grading Criteria With No Redistribution")
    void isBuildingTrivialCriteriaNoRedistribution() {
        // when:
        GradingCriteriaBuilder builder = new GradingCriteriaBuilder()
                .addComponent(new Component(FINAL_EXAM, 50.0))
                .addComponent(new Component(PARTIAL_EXAM, 10.0))
                .addComponent(new Component(PARTIAL_EXAM, 25.0))
                .withoutRedistribution();
        // when:
        PercentOverflowException actual =
                Assertions.assertThrows(PercentOverflowException.class, builder::build);
        // then:
        assertThat(actual.getMessage(), containsString("over the 100% limit"));
    }


    @Test
    @DisplayName("Building Grading Criteria With Adjusted Percentage")
    void isBuildingAdjustedCriteria() {
        // when:
        GradingCriteria actual = new GradingCriteriaBuilder()
                .addComponent(new Component(FINAL_EXAM, HIGH, 50.0))
                .addComponent(new Component(PARTIAL_EXAM, MEDIUM, 10.0))
                .addComponent(new Component(PARTIAL_EXAM, MEDIUM, 10.0))
                .build();
        // then:
        Assertions.assertAll("Is Adjusting Percentages",
                () -> Assertions.assertEquals(17.5, (double) actual.components().get(1).getPercent()),
                () -> Assertions.assertEquals(65.0, (double) actual.components().get(0).getPercent()),
                () -> Assertions.assertEquals(17.5, (double) actual.components().get(2).getPercent()),
                () -> Assertions.assertEquals(100.0, (double) getTotalPercentageFromCriteria(actual)));
    }

    @Test
    @DisplayName("Building Grading Criteria With OverflowException")
    void isBuildingCriteriaWithException() {
        // declarations:
        Component expected = new Component(PARTIAL_EXAM, 100.0);
        GradingCriteriaBuilder builder = new GradingCriteriaBuilder()
                .addComponent(expected)
                .addComponent(expected);
        // when:
        PercentOverflowException actual =
                Assertions.assertThrows(PercentOverflowException.class, builder::build);
        // then:
        assertThat(actual.getMessage(), containsString("over the 100% limit"));
    }

    @Test
    @DisplayName("Building Grading Criteria With Adjusted Random Percentage")
    void isBuildingAdjustedRandomCriteria() {
        IntStream.range(1, 100).forEach(this::isBuildingAdjustedRandomCriteriaOn);
    }

    private void isBuildingAdjustedRandomCriteriaOn(final Integer iteration) {
        // when:
        GradingCriteria actual = new GradingCriteriaBuilder()
                .addComponent(new Component(FINAL_EXAM, HIGH, generatePercentage.apply(40)))
                .addComponent(new Component(PARTIAL_EXAM, MEDIUM, generatePercentage.apply(20)))
                .addComponent(new Component(ASSIGNMENT, LOW, generatePercentage.apply(20)))
                .build();
        // then:
        Assertions.assertEquals(100.0, (double) getTotalPercentageFromCriteria(actual));
    }

    private Double getTotalPercentageFromCriteria(GradingCriteria actual) {
        return actual.components().stream()
                .map(Component::getPercent).reduce((acc, item) -> acc + item).orElse(0.0);
    }
}
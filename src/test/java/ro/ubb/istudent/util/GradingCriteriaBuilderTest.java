package ro.ubb.istudent.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ro.ubb.istudent.domain.Component;
import ro.ubb.istudent.domain.GradingCriteria;
import ro.ubb.istudent.exception.PercentOverflowException;
import ro.ubb.istudent.util.grading.criteria.GradingCriteriaBuilder;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ro.ubb.istudent.domain.ComponentImportance.*;
import static ro.ubb.istudent.domain.ComponentType.*;

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
        assertEquals(100.0, (double) getTotalPercentageFromCriteria(actual));
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
                assertThrows(PercentOverflowException.class, builder::build);
        // then:
        assertThat(actual.getMessage()).contains("over the 100% limit");
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
        assertAll("Is Adjusting Percentages",
                () -> assertEquals(65.0, (double) actual.getComponents().get(0).getPercent()),
                () -> assertEquals(17.5, (double) actual.getComponents().get(1).getPercent()),
                () -> assertEquals(17.5, (double) actual.getComponents().get(2).getPercent()),
                () -> assertEquals(100.0, (double) getTotalPercentageFromCriteria(actual)));
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
                assertThrows(PercentOverflowException.class, builder::build);
        // then:
        assertThat(actual.getMessage()).contains("over the 100% limit");
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
        assertEquals(100.0, (double) getTotalPercentageFromCriteria(actual));
    }

    private Double getTotalPercentageFromCriteria(GradingCriteria actual) {
        return actual.getComponents().stream()
                .map(Component::getPercent).reduce((acc, item) -> acc + item).orElse(0.0);
    }
}
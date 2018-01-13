package ro.ubb.istudent.grading.criteria;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.concurrent.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

import static ro.ubb.istudent.grading.criteria.GradingCriteriaComponentImportance.HIGH;
import static ro.ubb.istudent.grading.criteria.GradingCriteriaComponentType.FINAL_EXAM;

/**
 * @author Alexandru Stoica
 */

@ToString
@Immutable
@EqualsAndHashCode(of={"type", "percent", "importance"})
public class GradingCriteriaComponent implements Serializable {

    @JsonProperty
    private final GradingCriteriaComponentType type;

    @JsonProperty
    private final GradingCriteriaComponentImportance importance;

    @JsonProperty
    private final Double percent;

    public GradingCriteriaComponent(
            final GradingCriteriaComponentType type,
            final GradingCriteriaComponentImportance importance,
            final Double percent) {
        this.type = type;
        this.importance = importance;
        this.percent = percent;
    }

    public GradingCriteriaComponent() {
        this(FINAL_EXAM, HIGH, 100.0);
    }

    public GradingCriteriaComponentType type() {
        return type;
    }

    public GradingCriteriaComponentImportance importance() {
        return importance;
    }

    public Double percent() {
        return percent;
    }

    public GradingCriteriaComponent adjustPercentageWith(
            final Double lowestImportancePercentage) {
        return new GradingCriteriaComponent(type, importance,
                normalizeValueOf(lowestImportancePercentage * importance.value() + percent));
    }

    private double normalizeValueOf(final double value) {
        return (value - (long) value == 0.5) ? value : (double) Math.round(value);
    }
}

package ro.ubb.istudent.grading.criteria.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import javax.annotation.concurrent.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author Alexandru Stoica
 */

@Immutable
@ToString(of = {"gradingCriteriaComponents"})
@EqualsAndHashCode
public class GradingCriteria implements Serializable {

    @JsonProperty
    private final List<GradingCriteriaComponent> gradingCriteriaComponents;

    public GradingCriteria(
            final List<GradingCriteriaComponent> gradingCriteriaComponents) {
        this.gradingCriteriaComponents = gradingCriteriaComponents;
    }

    public GradingCriteria() {
        this.gradingCriteriaComponents = Collections.emptyList();
    }

    public List<GradingCriteriaComponent> components() {
        return ImmutableList.<GradingCriteriaComponent>builder()
                .addAll(gradingCriteriaComponents).build();
    }

    public Double totalPercentage() {
        return gradingCriteriaComponents.stream()
                .mapToDouble(GradingCriteriaComponent::percent)
                .sum();
    }
}

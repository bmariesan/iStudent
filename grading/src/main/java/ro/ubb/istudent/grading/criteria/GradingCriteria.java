package ro.ubb.istudent.grading.criteria;

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

    public GradingCriteria addGradingCriteriaComponent(
            final GradingCriteriaComponent component) {
        return new GradingCriteria(ImmutableList.<GradingCriteriaComponent>builder()
                .addAll(gradingCriteriaComponents).add(component).build());
    }

    public Double totalPercentage() {
        return gradingCriteriaComponents.stream()
                .mapToDouble(GradingCriteriaComponent::percent)
                .sum();
    }
}

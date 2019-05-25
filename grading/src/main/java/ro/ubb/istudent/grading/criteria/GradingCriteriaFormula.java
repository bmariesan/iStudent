package ro.ubb.istudent.grading.criteria;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ro.ubb.istudent.grading.exam.GradingFormula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@ToString(of = {"gradingCriteriaComponents"})
@EqualsAndHashCode
public class GradingCriteriaFormula implements GradingCriteria {

    @JsonProperty
    private final List<GradingCriteriaComponent> gradingCriteriaComponents;

    public GradingCriteriaFormula(
            final List<GradingCriteriaComponent> gradingCriteriaComponents) {
        this.gradingCriteriaComponents = gradingCriteriaComponents;
    }

    public GradingCriteriaFormula() {
        this.gradingCriteriaComponents = Collections.emptyList();
    }

    @Override
    public List<GradingCriteriaComponent> components() {
        return ImmutableList.<GradingCriteriaComponent>builder()
                .addAll(gradingCriteriaComponents).build();
    }

    @Override
    public GradingCriteria addGradingCriteriaComponent(
            final GradingCriteriaComponent component) {
        return new GradingCriteriaFormula(ImmutableList.<GradingCriteriaComponent>builder()
                .addAll(gradingCriteriaComponents).add(component).build());
    }

    @Override
    public Double totalPercentage() {
        return gradingCriteriaComponents.stream()
                .mapToDouble(GradingCriteriaComponent::percent)
                .sum();
    }
}

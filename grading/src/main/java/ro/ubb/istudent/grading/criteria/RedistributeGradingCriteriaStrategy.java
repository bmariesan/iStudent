package ro.ubb.istudent.grading.criteria;

import ro.ubb.istudent.grading.exception.PercentageOverflow;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RedistributeGradingCriteriaStrategy
        implements GradingCriteriaCreatorStrategy {

    @Override
    public Optional<GradingCriteria> createBasedOn(
            final GradingCriteria gradingCriteria) {
        return Optional.of(new GradingCriteriaFormula(redistributeGradingCriteriaComponents(
                gradingCriteria.totalPercentage(), gradingCriteria.components())));
    }

    private List<GradingCriteriaComponent> redistributeGradingCriteriaComponents(
            final Double totalPercentage,
            final List<GradingCriteriaComponent> gradingCriteriaComponents) {
        if (totalPercentage > 100) throw new PercentageOverflow();
        return totalPercentage < 100.0 ?
                redistributePercentage(totalPercentage, gradingCriteriaComponents) :
                gradingCriteriaComponents;
    }

    private List<GradingCriteriaComponent> redistributePercentage(
            final Double totalPercentage,
            final List<GradingCriteriaComponent> components) {
        return components.stream()
                .map(component -> component.adjustPercentageWith(
                        getLowestImportancePercentage(totalPercentage, components)))
                .collect(Collectors.toList());
    }

    private Double getLowestImportancePercentage(
            final Double totalPercentage,
            final List<GradingCriteriaComponent> components) {
        return (100.0 - totalPercentage) / components.stream()
                .mapToInt(component -> component.importance().value()).sum();
    }
}

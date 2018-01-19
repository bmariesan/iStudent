package ro.ubb.istudent.grading.criteria;

import javax.annotation.concurrent.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ro.ubb.istudent.grading.exception.PercentageOverflow;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 */

@ToString(of = {"gradingCriteria"})
@EqualsAndHashCode(callSuper = true)
public class RedistributedGradingCriteria extends GradingCriteria {

    private final GradingCriteria gradingCriteria;

    // TODO: Move logic from constructor to lazy lambda function.
    public RedistributedGradingCriteria(final GradingCriteria gradingCriteria) {
        super(gradingCriteria.components());
        this.gradingCriteria =
                createGradingCriteriaWithRedistribution(gradingCriteria);
    }

    @Override
    public List<GradingCriteriaComponent> components() {
        return gradingCriteria.components();
    }

    @Override
    public Double totalPercentage() {
        return gradingCriteria.totalPercentage();
    }

    private GradingCriteria createGradingCriteriaWithRedistribution(
            final GradingCriteria base) {
        return new GradingCriteria(redistributeGradingCriteriaComponents(
                base.totalPercentage(), base.components()));
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

package ro.ubb.istudent.grading.criteria;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;

/**
 * @author Alexandru Stoica
 */

@Immutable
@EqualsAndHashCode(callSuper = true)
public class GradingCriteriaWithValidatedPercentage extends GradingCriteria {

    public GradingCriteriaWithValidatedPercentage(
            final GradingCriteria gradingCriteria) {
        super(gradingCriteria.components());
        if (gradingCriteria.totalPercentage() > 100.0)
            throw new PercentageOverflow();
    }
}

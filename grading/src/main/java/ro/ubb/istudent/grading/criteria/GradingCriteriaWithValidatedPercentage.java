package ro.ubb.istudent.grading.criteria;

import javax.annotation.concurrent.Immutable;
import lombok.EqualsAndHashCode;
import ro.ubb.istudent.grading.exception.PercentageOverflow;

/**
 * @author Alexandru Stoica
 */

@EqualsAndHashCode(callSuper = true)
public class GradingCriteriaWithValidatedPercentage extends GradingCriteria {

    public GradingCriteriaWithValidatedPercentage(
            final GradingCriteria gradingCriteria) {
        super(gradingCriteria.components());
        if (gradingCriteria.totalPercentage() != 100.0)
            throw new PercentageOverflow();
    }
}

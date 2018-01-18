package ro.ubb.istudent.grading.criteria.domain;

import javax.annotation.concurrent.Immutable;
import lombok.EqualsAndHashCode;
import ro.ubb.istudent.grading.criteria.exception.PercentageOverflow;

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

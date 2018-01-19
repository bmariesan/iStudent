package ro.ubb.istudent.grading.criteria;

import javax.annotation.concurrent.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ro.ubb.istudent.grading.exception.PercentageOverflow;

/**
 * @author Alexandru Stoica
 */

@ToString(of = {"gradingCriteria"})
@EqualsAndHashCode(callSuper = true)
public class GradingCriteriaWithValidatedPercentage extends GradingCriteria {

    private final GradingCriteria gradingCriteria;

    public GradingCriteriaWithValidatedPercentage(
            final GradingCriteria gradingCriteria) {
        super(gradingCriteria.components());
        this.gradingCriteria = gradingCriteria;
        if (gradingCriteria.totalPercentage() != 100.0)
            throw new PercentageOverflow();
    }
}

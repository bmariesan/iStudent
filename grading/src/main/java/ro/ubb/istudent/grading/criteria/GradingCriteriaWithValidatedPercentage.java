package ro.ubb.istudent.grading.criteria;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ro.ubb.istudent.grading.exception.PercentageOverflow;

/**
 * @author Alexandru Stoica
 */

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GradingCriteriaWithValidatedPercentage
        extends GradingCriteriaDecorator {

    public GradingCriteriaWithValidatedPercentage(
            final GradingCriteria gradingCriteria) {
        super(new CheckPercentageOverflowStrategy()
                .createBasedOn(gradingCriteria)
                .orElseThrow(PercentageOverflow::new));
    }
}

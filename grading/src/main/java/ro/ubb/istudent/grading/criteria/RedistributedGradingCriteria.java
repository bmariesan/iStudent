package ro.ubb.istudent.grading.criteria;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ro.ubb.istudent.grading.exception.PercentageOverflow;

/**
 * @author Alexandru Stoica
 */

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RedistributedGradingCriteria
        extends GradingCriteriaDecorator {

    public RedistributedGradingCriteria(final GradingCriteria gradingCriteria) {
        super(new RedistributeGradingCriteriaStrategy()
                .createBasedOn(gradingCriteria)
                .orElseThrow(PercentageOverflow::new));
    }
}

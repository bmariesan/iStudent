package ro.ubb.istudent.grading.criteria;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ro.ubb.istudent.grading.exam.UnitOfWorkWithCompletedExercises;
import ro.ubb.istudent.grading.exception.PercentageOverflow;

import java.util.List;
import java.util.stream.Collectors;

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

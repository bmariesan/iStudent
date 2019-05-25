package ro.ubb.istudent.grading.criteria;

import java.util.Optional;

public class CheckPercentageOverflowStrategy
        implements GradingCriteriaCreatorStrategy {

    @Override
    public Optional<GradingCriteria> createBasedOn(
            final GradingCriteria gradingCriteria) {
        return gradingCriteria.totalPercentage() != 100.0 ?
                Optional.empty() : Optional.of(gradingCriteria);
    }
}

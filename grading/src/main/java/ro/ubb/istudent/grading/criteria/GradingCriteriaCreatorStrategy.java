package ro.ubb.istudent.grading.criteria;

import java.util.Optional;

/**
 * @author Alexandru Stoica
 */

interface GradingCriteriaCreatorStrategy {
    Optional<GradingCriteria> createBasedOn(final GradingCriteria gradingCriteria);
}

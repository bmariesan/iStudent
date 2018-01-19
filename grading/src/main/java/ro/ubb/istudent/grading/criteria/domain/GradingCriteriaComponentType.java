package ro.ubb.istudent.grading.criteria.domain;

import javax.annotation.concurrent.Immutable;
import java.io.Serializable;

/**
 * @author Alexandru Stoica
 */

@Immutable
public enum GradingCriteriaComponentType implements Serializable {
    FINAL_EXAM, PARTIAL_EXAM, ASSIGNMENT
}

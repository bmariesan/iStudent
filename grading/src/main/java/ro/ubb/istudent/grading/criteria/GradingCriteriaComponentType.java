package ro.ubb.istudent.grading.criteria;

import javax.annotation.concurrent.Immutable;
import java.io.Serializable;

/**
 * @author Alexandru Stoica
 */

public enum GradingCriteriaComponentType implements Serializable {
    FINAL_EXAM, PARTIAL_EXAM, ASSIGNMENT
}

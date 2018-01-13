package ro.ubb.istudent.grading.criteria;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 */

@Immutable
public enum GradingCriteriaComponentType implements Serializable {
    FINAL_EXAM, PARTIAL_EXAM, ASSIGNMENT
}

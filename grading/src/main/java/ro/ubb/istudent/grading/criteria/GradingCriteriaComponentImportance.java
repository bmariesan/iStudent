package ro.ubb.istudent.grading.criteria;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 */

@Immutable
public enum GradingCriteriaComponentImportance implements Serializable {

    HIGH(4), MEDIUM(2), LOW(1), OPTIONAL(0);

    private final Integer value;

    private GradingCriteriaComponentImportance(final Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }
}

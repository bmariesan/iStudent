package ro.ubb.istudent.grading.criteria;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 */

public enum GradingCriteriaComponentImportance implements Serializable {

    HIGH(4), MEDIUM(2), LOW(1), OPTIONAL(0);

    private final Integer value;

    GradingCriteriaComponentImportance(final Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }
}

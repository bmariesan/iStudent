package ro.ubb.istudent.grading.criteria.component;

/**
 * <p>Describes the importance of a component from the grading criteria.</p>
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum ComponentImportance {

    HIGH(4), MEDIUM(2), LOW(1), OPTIONAL(0);

    private final Integer value;

    private ComponentImportance(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }
}

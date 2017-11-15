package ro.ubb.istudent.domain;

/**
 * <p>Describes the priority of a component.</p>
 * <p>
 * The provided value represents how much the property matters
 * compared to LOW (with is the atom of value 1)
 * </p>
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum ComponentPriority {

    HIGH(4), MEDIUM(2), LOW(1), OPTIONAL(0);

    private final Integer value;

    private ComponentPriority(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}

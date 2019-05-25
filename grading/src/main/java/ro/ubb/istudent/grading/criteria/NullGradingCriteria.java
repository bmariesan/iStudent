package ro.ubb.istudent.grading.criteria;

import static java.util.Collections.singletonList;

/**
 * @author Alexandru Stoica
 */

public class NullGradingCriteria extends GradingCriteriaDecorator {

    public NullGradingCriteria() {
        super(new GradingCriteriaFormula(singletonList(new GradingCriteriaComponent(
                GradingCriteriaComponentType.ASSIGNMENT,
                GradingCriteriaComponentImportance.LOW, 100.0))));
    }
}

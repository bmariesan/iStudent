package ro.ubb.istudent.grading.criteria;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GradingCriteriaFormula.class,
                name = "formula"),
        @JsonSubTypes.Type(value = GradingCriteriaDecorator.class,
                name = "decorator")})
public interface GradingCriteria extends Serializable {
    List<GradingCriteriaComponent> components();
    GradingCriteria addGradingCriteriaComponent(
            GradingCriteriaComponent component);
    Double totalPercentage();
}

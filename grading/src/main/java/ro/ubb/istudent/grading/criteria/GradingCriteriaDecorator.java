package ro.ubb.istudent.grading.criteria;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "@decorator-class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GradingCriteriaWithValidatedPercentage.class,
                name = "overflow-criteria-check"),
        @JsonSubTypes.Type(value = RedistributedGradingCriteria.class,
                name = "redistribution-criteria-check")})
public class GradingCriteriaDecorator implements GradingCriteria {

    @JsonProperty("criteria")
    protected final GradingCriteria gradingCriteria;

    public GradingCriteriaDecorator(
            final GradingCriteria gradingCriteria) {
        this.gradingCriteria = gradingCriteria;
    }

    @Override
    public List<GradingCriteriaComponent> components() {
        return gradingCriteria.components();
    }

    @Override
    public GradingCriteria addGradingCriteriaComponent(
            final GradingCriteriaComponent component) {
        return gradingCriteria.addGradingCriteriaComponent(component);
    }

    @Override
    public Double totalPercentage() {
        return gradingCriteria.totalPercentage();
    }
}

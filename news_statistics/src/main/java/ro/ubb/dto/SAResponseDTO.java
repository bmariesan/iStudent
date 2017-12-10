package ro.ubb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SAResponseDTO {
    String label;
    ProbabilityDTO probability;

    public SAResponseDTO(String label, ProbabilityDTO probability) {
        this.label = label;
        this.probability = probability;
    }

    public SAResponseDTO() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ProbabilityDTO getProbability() {
        return probability;
    }

    public void setProbability(ProbabilityDTO probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "SAResponseDTO{" +
                "label='" + label + '\'' +
                ", probability=" + probability +
                '}';
    }
}

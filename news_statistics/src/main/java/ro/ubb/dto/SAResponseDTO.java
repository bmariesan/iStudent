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

    public void combineWith(SAResponseDTO partialRes, int count) {
        if(count == 0){
            this.setLabel(partialRes.getLabel());
            this.setProbability(partialRes.getProbability());
        }
        else {
            this.probability.setNeg((this.probability.getNeg() * count + partialRes.getProbability().getNeg()) / (count + 1));
            this.probability.setNeutral((this.probability.getNeutral() * count + partialRes.getProbability().getNeutral()) / (count + 1));
            this.probability.setPos((this.probability.getPos() * count + partialRes.getProbability().getPos()) / (count + 1));
        }
    }

    @Override
    public String toString() {
        return "SAResponseDTO{" +
                "label='" + label + '\'' +
                ", probability=" + probability +
                '}';
    }
}

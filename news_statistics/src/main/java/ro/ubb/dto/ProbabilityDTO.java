package ro.ubb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProbabilityDTO {
    private Double neg, neutral, pos;

    public ProbabilityDTO(Double neg, Double neutral, Double pos) {
        this.neg = neg;
        this.neutral = neutral;
        this.pos = pos;
    }

    public ProbabilityDTO() {
    }

    public Double getNeg() {
        return neg;
    }

    public void setNeg(Double neg) {
        this.neg = neg;
    }

    public Double getNeutral() {
        return neutral;
    }

    public void setNeutral(Double neutral) {
        this.neutral = neutral;
    }

    public Double getPos() {
        return pos;
    }

    public void setPos(Double pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "ProbabilityDTO{" +
                "neg=" + neg +
                ", neutral=" + neutral +
                ", pos=" + pos +
                '}';
    }
}

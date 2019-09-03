package exams.domain.statistics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StringTuple {

    private String name, average;

    public StringTuple(){

    }
    public StringTuple(String name, String average) {
        this.name = name;
        this.average = average;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

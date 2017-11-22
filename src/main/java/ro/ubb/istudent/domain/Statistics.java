package ro.ubb.istudent.domain;

import com.sun.org.glassfish.external.statistics.Statistic;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by usr on 22.11.2017.
 */
public class Statistics implements Serializable {

    private HashMap<String,Integer> result;
    private Integer year;

    public Statistics(HashMap<String, Integer> result, Integer year) {
        this.result = result;
        this.year = year;
    }

    public HashMap<String, Integer> getResult() {
        return result;
    }

    public void setResult(HashMap<String, Integer> result) {
        this.result = result;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistics that = (Statistics) o;

        if (!result.equals(that.result)) return false;
        return year.equals(that.year);
    }

    @Override
    public int hashCode() {
        int result1 = result.hashCode();
        result1 = 31 * result1 + year.hashCode();
        return result1;
    }
}

package ro.ubb.istudent.domain;
import ro.ubb.istudent.dto.Dto;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by usr on 22.11.2017.
 */
public class Statistics implements Dto {

    private HashMap<String,Integer> result;
    private Integer year;

    public Statistics(HashMap<String, Integer> result, Integer year) {
        this.result = result;
        this.year = year;
    }

    public Statistics(){
        this.result = new HashMap<String,Integer>();
        this.year = 2018;
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

    public String ToHTML(){
        String s = "<h3>" + this.year + "</h3>";
        for (HashMap.Entry<String, Integer> entry : this.result.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            s = s + "<h4>"  + entry.getKey() +": " + entry.getValue() + "</h4>";
        }
        s+="<br/>";
        return s;
    }

    public String toPDF(){
        String s = this.year.toString() + "\n";
        for(HashMap.Entry<String,Integer> entry : this.result.entrySet()){
            s = s + entry.getKey() +": " + entry.getValue() + "\n";
        }
        return s;
    }
}

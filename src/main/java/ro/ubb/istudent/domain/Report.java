package ro.ubb.istudent.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import ro.ubb.istudent.dto.Dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by usr on 22.11.2017.
 */

public class Report implements Dto {

    private ArrayList<Statistics> statistics;
    private String title;


    public Report(ArrayList<Statistics> statistics, String title) {
        this.statistics = statistics;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Report(){
        this.statistics = new ArrayList<Statistics>();
        this.title = "";

    }

    public ArrayList<Statistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(ArrayList<Statistics> statistics) {
        this.statistics = statistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        return statistics.equals(report.statistics);
    }

    @Override
    public int hashCode() {
        return statistics.hashCode();
    }

    public String ToHTML(){
        String s = "<h3>" + this.title + "</h3><ul>";
        for (Statistics entry : this.statistics) {
           s+= "<li" + entry.ToHTML() + "</li>";
        }
        s+="</ul><br/>";
        return s;
    }
}

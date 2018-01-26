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
//
//    public Report(ArrayList<Statistics> statistics) {
//        this.statistics = statistics;
//    }

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
}

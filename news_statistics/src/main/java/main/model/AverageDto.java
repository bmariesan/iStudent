package main.model;


import java.util.Date;

/**
 * Created by Vali on 10.12.2017.
 */
public class AverageDto {
    private Date date;
    private Double average;

    public AverageDto(Date date, Double average) {
        this.date = date;
        this.average = average;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }
}

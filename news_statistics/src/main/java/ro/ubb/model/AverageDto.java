package ro.ubb.model;


import java.util.Date;

/**
 * Created by Vali on 10.12.2017.
 */
public class AverageDto {
    private Date date;
    private Double average;
    private Double weight;


    public AverageDto(AverageDtoBuilder builder) {
        date = builder.date;
        average = builder.average;
        weight = builder.weight;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
    public static AverageDtoBuilder builder() {
        return new AverageDtoBuilder();
    }

    public static class AverageDtoBuilder{
        private Date date;
        private Double average;
        private Double weight;

        public AverageDtoBuilder() {

        }
        public  AverageDtoBuilder  date(Date date) {
            this.date = date;
            return this;
        }

        public  AverageDtoBuilder average(Double average) {
            this.average = average;
            return  this;
        }

        public AverageDtoBuilder weight(Double weight) {
            this.weight = weight;
            return  this;
        }

        public AverageDto build() {
            return new AverageDto(this);
        }
    }
}

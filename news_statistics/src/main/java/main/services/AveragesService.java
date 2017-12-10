package main.services;

import main.model.AverageDto;
import main.model.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Created by Vali on 10.12.2017.
 */
public class AveragesService {

    public List<AverageDto> getSimpleMovingAverages(List<Pair<Date, List<Double>>> ratingPerDates, Date date) {
        List<AverageDto> simpleMovingAverages;
        simpleMovingAverages = ratingPerDates.stream()
                .map(dateRatings -> new AverageDto(dateRatings.getX(), computeMean(dateRatings.getY())))
                .collect(Collectors.toList());
        return simpleMovingAverages;
    }

    private Double computeMean(List<Double> values) {
        OptionalDouble average = values
                .stream()
                .mapToDouble(a -> a)
                .average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }
}

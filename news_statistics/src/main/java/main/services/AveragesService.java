package main.services;

import main.model.AverageDto;
import main.model.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Created by Vali on 10.12.2017.
 */
@Service
public class AveragesService {

    public List<AverageDto> getSimpleMovingAverages(List<Pair<Date, List<Double>>> ratingsPerDates) {
        List<AverageDto> simpleMovingAverages;
        simpleMovingAverages = ratingsPerDates.stream()
                .map(dateRatings -> AverageDto.builder()
                        .date(dateRatings.getX())
                        .average(computeMean(dateRatings.getY()))
                        .build())
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

    //values must be sorted by date
    public List<AverageDto> getExponentialovingAverages(List<Pair<Date, List<Double>>> ratingsPerDate, Date currentDate) {
        List<AverageDto> exponentialMovingAverages = new ArrayList<>();
        Date comparingDate = ratingsPerDate.get(0).getX();
        Double weight = 1.0 / ratingsPerDate.size();

        for (Pair<Date, List<Double>> ratingPerDay : ratingsPerDate) {
            double mean = computeMean(ratingPerDay.getY());
            double newWeight = weight;
            AverageDto averageDto = AverageDto.builder()
                    .average(mean)
                    .date(ratingPerDay.getX())
                    .weight(newWeight)
                    .build();
            averageDto.setWeight(newWeight);
            exponentialMovingAverages.add(averageDto);
            weight = 2 * weight;

        }

        return exponentialMovingAverages;

    }
}

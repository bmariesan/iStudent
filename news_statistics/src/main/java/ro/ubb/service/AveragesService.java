package ro.ubb.service;

import ro.ubb.model.AverageDto;
import ro.ubb.model.Pair;
import ro.ubb.utils.SortByDateComparator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
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
        OptionalDouble average = values.stream().mapToDouble(a -> a).average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }

    public TreeSet<AverageDto> getExponentialMovingAverages(List<Pair<Date, List<Double>>> ratingsPerDate,
                                                            Date currentDate) {

        TreeSet sortedByDateResults = new TreeSet(new SortByDateComparator());

        sortedByDateResults.addAll(ratingsPerDate.stream().map((ratingPerDay) -> {
            double mean = computeMean(ratingPerDay.getY());
            double weight = computeWeight(ratingPerDay, currentDate);
            return AverageDto.builder().average(mean).date(ratingPerDay.getX()).weight(weight).build();
        }).collect(Collectors.toSet()));
        return sortedByDateResults;
    }

    private double computeWeight(Pair<Date, List<Double>> ratingPerDay, Date currentDate) {
        long diffInMilliSeconds = currentDate.getTime() - ratingPerDay.getX().getTime();
        return 1.0 / TimeUnit.DAYS.convert(diffInMilliSeconds, TimeUnit.MILLISECONDS);
    }
}

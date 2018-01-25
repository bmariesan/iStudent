package exams.rest;

import exams.StatisticFactory.IStatisticFactory;
import exams.StatisticFactory.StatisticFactory;
import exams.domain.Exam;
import exams.domain.statistics.IStatistic;
import exams.service.Service;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/api")
@RestController
public class StatisticResource {
    private static final String STATISTICS_CONTROLLER_MAPPING = "/statistics/{criteria}";
    private final Service service;
    private final String baseUrl;
    private IStatisticFactory factory = new StatisticFactory();

    public StatisticResource(Service service, @Value("${application.base-url}") String baseUrl) {
        this.service = service;
        this.baseUrl = baseUrl;
    }

    @GetMapping(STATISTICS_CONTROLLER_MAPPING)
    public IStatistic getStatistic(@PathVariable String criteria){
        return factory.getStatistic(criteria, service);
    }
}

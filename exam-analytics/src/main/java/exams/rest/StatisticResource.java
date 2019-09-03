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
import java.util.logging.Logger;

@RequestMapping("/api")
@RestController
public class StatisticResource {
    private static final String STATISTICS_CONTROLLER_MAPPING = "/statistics";
    private static final String CRITERIA = "/{criteria}";
    private static final String EXAM_ID = "/{examID}";
    private static final String EXAMS = "/exams";
    private final Service service;
    private final String baseUrl;
    private Logger logger = Logger.getLogger("INFO");
    private IStatisticFactory factory = new StatisticFactory();

    public StatisticResource(Service service, @Value("${application.base-url}") String baseUrl) {
        this.service = service;
        this.baseUrl = baseUrl;
    }

    @GetMapping(STATISTICS_CONTROLLER_MAPPING+CRITERIA)
    public IStatistic getStatistic(@PathVariable String criteria){
        logger.info("Statistics Generation...");
        return factory.getStatistic(criteria, service);
    }

    //todo bayes statistics divide by zero error needs solving
    @GetMapping(STATISTICS_CONTROLLER_MAPPING+CRITERIA+EXAM_ID)
    public IStatistic getBayesStatistic(@PathVariable String criteria, @PathVariable String examID){
        logger.info("Bayes Statistics Generation...");
        return factory.getBayesStatistics(Integer.parseInt(examID), criteria, service);
    }

    @GetMapping(EXAMS)
    public List<Exam> getExams(){
        return service.getExams();
    }
}

package exams.StatisticFactory;


import exams.domain.statistics.*;
import exams.domain.statistics.bayesStatistics.BayesAges;
import exams.domain.statistics.bayesStatistics.BayesCountries;
import exams.domain.statistics.bayesStatistics.BayesGender;
import exams.repository.ExamRepository;
import exams.repository.StudentRepository;
import exams.service.Service;

import java.util.logging.Logger;

public class StatisticFactory implements IStatisticFactory{
    private Logger logger = Logger.getLogger("INFO");
    public IStatistic getStatistic(String criteria, Service service){
        if ( criteria.equals("exam")) {
            logger.info("Exam statistic generation");
            return new ExamStatistic(service);
        }
        else if(criteria.equals("age")) {
            logger.info("Age statistic generation");
            return new AgeStatistic(service);
        }
        else if ( criteria.equals("country")) {
            logger.info("Country statistic generation");
            return new CountryStatistic(service);
        }
        else if ( criteria.equals("gender")){
            logger.info("Gender statistic generation");
            return new GenderStatistic(service);
        }
        logger.severe("Invalid criteria");
        return null;
    }

    @Override
    public IStatistic getBayesStatistics(int idExam, String criteria, Service service) {
        if ( criteria.equals("bayesAge")) {
            logger.info("Bayes Age statistic generation");
            return new BayesAges(service,idExam);
        }
        else if ( criteria.equals("bayesCountry")) {
            logger.info("Bayes Country statistic generation");
            return new BayesCountries(service,idExam);
        }
        else if ( criteria.equals("bayesGender")){
            logger.info("Bayes Gender statistic generation");
            return new BayesGender(service,idExam);
        }
        logger.severe("Invalid criteria");
        return null;
    }


}

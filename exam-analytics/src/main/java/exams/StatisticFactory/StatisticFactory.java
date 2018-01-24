package exams.StatisticFactory;


import exams.domain.statistics.*;
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
}

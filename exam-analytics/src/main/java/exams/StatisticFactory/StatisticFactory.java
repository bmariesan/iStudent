package exams.StatisticFactory;


import exams.domain.statistics.*;
import exams.repository.ExamRepository;
import exams.repository.StudentRepository;

public class StatisticFactory implements IStatisticFactory{

    public IStatistic getStatistic(String criteria, ExamRepository examRepository, StudentRepository studentRepository){
        if ( criteria.equals("exam"))
            return new ExamStatistic(examRepository);
        else if(criteria.equals("age"))
            return new AgeStatistic(studentRepository);
        else if ( criteria.equals("country"))
            return new CountryStatistic(studentRepository);
        else if ( criteria.equals("gender"))
            return new GenderStatistic(studentRepository);

        return null;
    }
}

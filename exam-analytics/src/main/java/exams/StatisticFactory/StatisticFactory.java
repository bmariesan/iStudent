package exams.StatisticFactory;


import exams.domain.statistics.*;
import exams.repository.ExamRepository;
import exams.repository.StudentRepository;
import exams.service.Service;

public class StatisticFactory implements IStatisticFactory{

    public IStatistic getStatistic(String criteria, Service service){
        if ( criteria.equals("exam"))
            //aici ne mai trebuie obiect exam sau id/titlu pt statistica e pt un examen
            return new ExamStatistic(service);
        else if(criteria.equals("age"))
            return new AgeStatistic(service);
        else if ( criteria.equals("country"))
            return new CountryStatistic(service);
        else if ( criteria.equals("gender"))
            return new GenderStatistic(service);

        return null;
    }
}

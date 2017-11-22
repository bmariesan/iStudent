package ro.ubb.istudent.StatisticFactory;

import ro.ubb.istudent.domain.*;
import ro.ubb.istudent.repository.CourseRepository;
import ro.ubb.istudent.repository.StudentRepository;

public class StatisticFactory implements IStatisticFactory{

    public IStatistic getStatistic(String criteria, CourseRepository courseRepository, StudentRepository studentRepository){
        if ( criteria.equals("course"))
            return new CourseStatistic(courseRepository, studentRepository);
        else if(criteria.equals("age"))
            return new AgeStatistic(studentRepository);
        else if ( criteria.equals("country"))
            return new CountryStatistic(studentRepository);
        else if ( criteria.equals("gender"))
            return new GenderStatistic(studentRepository);

        return null;
    }
}

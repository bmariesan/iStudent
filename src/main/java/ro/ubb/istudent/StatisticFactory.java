package ro.ubb.istudent;

import ro.ubb.istudent.domain.*;
import ro.ubb.istudent.repository.CourseRepository;
import ro.ubb.istudent.repository.StudentRepository;

public class StatisticFactory {

    public static IStatistic getStatistic(String criteria, CourseRepository courseRepository, StudentRepository studentRepository){
        if ( criteria.equals("average") )
            return new AverageStatistic(courseRepository, studentRepository);
        else if ( criteria.equals("age") )
            return new AgeStatistic(studentRepository);
        else if ( criteria.equals("country") )
            return new CountryStatistic(studentRepository);
        else if ( criteria.equals("gender") )
            return new GenderStatistic(studentRepository);

        return null;
    }
}

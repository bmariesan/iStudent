package ro.ubb.istudent.StatisticFactory;

import ro.ubb.istudent.domain.*;

public class StatisticFactory implements IStatisticFactory{

    public  IStatistic getStatistic(String criteria){
        if ( criteria.equals("course") )
            return new CourseStatistic();
        else if ( criteria.equals("age") )
            return new AgeStatistic();
        else if ( criteria.equals("country") )
            return new CountryStatistic();
        else if ( criteria.equals("gender") )
            return new GenderStatistic();

        return null;
    }
}

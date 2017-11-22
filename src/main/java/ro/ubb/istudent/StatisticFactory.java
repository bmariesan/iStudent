package ro.ubb.istudent;

import ro.ubb.istudent.domain.*;

public class StatisticFactory {

    public static IStatistic getStatistic(String criteria){
        if ( criteria.equals("average") )
            return new AverageStatistic();
        else if ( criteria.equals("age") )
            return new AgeStatistic();
        else if ( criteria.equals("country") )
            return new CountryStatistic();
        else if ( criteria.equals("gender") )
            return new GenderStatistic();

        return null;
    }
}

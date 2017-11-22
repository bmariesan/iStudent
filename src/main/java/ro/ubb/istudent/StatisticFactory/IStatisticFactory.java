package ro.ubb.istudent.StatisticFactory;

import ro.ubb.istudent.domain.IStatistic;

/**
 * Created by Teodora on 22/11/2017.
 */
public interface IStatisticFactory {
    IStatistic getStatistic(String criteria);
}

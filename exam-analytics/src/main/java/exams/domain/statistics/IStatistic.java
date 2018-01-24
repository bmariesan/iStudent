package exams.domain.statistics;

import java.util.Map;

public interface IStatistic {
    void generateStatistic();
    Map<String,Float> getData();
}

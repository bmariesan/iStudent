package exams.domain;

import java.util.Map;

public interface IStatistic {
    IStatistic generateStatistic();
    Map<String,Float> getData();
}

package ro.ubb.istudent.domain;

import java.util.Map;

public class CourseStatistic implements IStatistic {
    private Map<String, Float> studentAverage;


    public CourseStatistic(){

    }


    @Override
    public IStatistic generateStatistic() {
        return null;
    }

    @Override
    public Map<String, Float> getData() {
        return null;
    }


}

package ro.ubb.istudent.domain;

import ro.ubb.istudent.repository.CourseRepository;
import ro.ubb.istudent.repository.StudentRepository;

import java.util.Map;

public class CourseStatistic implements IStatistic {
    private Map<String, Integer> studentAverageMap;


    public CourseStatistic(CourseRepository courseRepository, StudentRepository studentRepository){

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

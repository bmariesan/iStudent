package ro.ubb.istudent.domain;

import ro.ubb.istudent.repository.CourseRepository;
import ro.ubb.istudent.repository.StudentRepository;

import java.util.Map;

public class AverageStatistic implements IStatistic {
    private Map<String, Integer> studentAverageMap;


    public AverageStatistic(CourseRepository courseRepository, StudentRepository studentRepository){

    }


    @Override
    public IStatistic generateStatistic() {
        return null;
    }
}

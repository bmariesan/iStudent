package service;

import StatisticFactory.IStatisticFactory;
import org.springframework.stereotype.Service;
import repository.CourseRepository;
import repository.StudentRepository;

@Service
public class StatisticService {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private IStatisticFactory statisticFactory;

    public StatisticService(CourseRepository courseRepository, StudentRepository studentRepository){
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }


}

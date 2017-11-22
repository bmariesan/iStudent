package ro.ubb.istudent.service;

import org.springframework.stereotype.Service;
import ro.ubb.istudent.StatisticFactory.IStatisticFactory;
import ro.ubb.istudent.repository.CourseRepository;
import ro.ubb.istudent.repository.StudentRepository;

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

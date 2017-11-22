package ro.ubb.istudent.StatisticFactory;

import ro.ubb.istudent.domain.IStatistic;
import ro.ubb.istudent.repository.CourseRepository;
import ro.ubb.istudent.repository.StudentRepository;

/**
 * Created by Teodora on 22/11/2017.
 */
public interface IStatisticFactory {
    IStatistic getStatistic(String criteria, CourseRepository courseRepository, StudentRepository studentRepository);
}

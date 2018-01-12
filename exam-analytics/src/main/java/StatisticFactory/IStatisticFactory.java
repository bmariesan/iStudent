package StatisticFactory;


import domain.IStatistic;
import repository.CourseRepository;
import repository.StudentRepository;

/**
 * Created by Teodora on 22/11/2017.
 */
public interface IStatisticFactory {
    IStatistic getStatistic(String criteria, CourseRepository courseRepository, StudentRepository studentRepository);
}

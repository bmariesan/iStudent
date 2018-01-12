package exams.StatisticFactory;


import exams.domain.IStatistic;
import exams.repository.CourseRepository;
import exams.repository.StudentRepository;

/**
 * Created by Teodora on 22/11/2017.
 */
public interface IStatisticFactory {
    IStatistic getStatistic(String criteria, CourseRepository courseRepository, StudentRepository studentRepository);
}

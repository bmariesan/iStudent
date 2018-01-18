package exams.StatisticFactory;


import exams.domain.statistics.IStatistic;
import exams.repository.ExamRepository;
import exams.repository.StudentRepository;

/**
 * Created by Teodora on 22/11/2017.
 */
public interface IStatisticFactory {
    IStatistic getStatistic(String criteria, ExamRepository examRepository, StudentRepository studentRepository);
}

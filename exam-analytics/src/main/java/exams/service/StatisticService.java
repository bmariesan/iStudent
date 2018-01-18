package exams.service;

import exams.StatisticFactory.IStatisticFactory;
import org.springframework.stereotype.Service;
import exams.repository.ExamRepository;
import exams.repository.StudentRepository;

@Service
public class StatisticService {
   // private ExamRepository examRepository;
   // private StudentRepository studentRepository;
    private IStatisticFactory statisticFactory;

   /* public StatisticService(ExamRepository examRepository, StudentRepository studentRepository){
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
    }*/


}

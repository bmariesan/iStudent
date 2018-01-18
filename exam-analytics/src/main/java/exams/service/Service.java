package exams.service;

import exams.domain.Exam;
import exams.repository.ExamRepository;

/**
 * Created by Teodora on 18/01/2018.
 */
public class ExamService {

    private final ExamRepository repository;

    public ExamService(ExamRepository repository) {
        this.repository = repository;
    }

    private void createExams(){
        repository.save()
    }

}

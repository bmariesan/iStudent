package ro.ubb.istudent.grading.service;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.grading.domain.Exam;
import ro.ubb.istudent.grading.repository.CompletedExecrciseRepository;
import ro.ubb.istudent.grading.repository.ExamRepository;

/**
 * @author Alexandru Arnautu
 */

@Service
@Immutable
public class ExamService
{

    @Autowired
    private final ExamRepository examRepository;

    public ExamService(final ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public Exam insert(final Exam exam) {
        return examRepository.save(exam);
    }

    public void delete(final ObjectId examId) {
        examRepository.delete(examId);
    }

    public Exam update(final Exam exam) {
        return examRepository.save(exam);
    }

    public Exam getExamById(final ObjectId examId) {
        return examRepository.findOne(examId);
    }

    private Exam save(final Exam criteria) {
        return examRepository.save(criteria);
    }
}

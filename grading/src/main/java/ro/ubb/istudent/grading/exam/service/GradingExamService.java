package ro.ubb.istudent.grading.exam.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.grading.exam.domain.CompletedExam;
import ro.ubb.istudent.grading.gradingbook.domain.Grade;
import ro.ubb.istudent.grading.gradingbook.domain.NormalGrade;
import ro.ubb.istudent.grading.gradingbook.domain.Student;
import ro.ubb.istudent.grading.gradingbook.domain.Teacher;

@Service
public class GradingExamService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradingExamService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public NormalGrade grade(CompletedExam exam) {
        return gradeRepository.save(new NormalGrade(ObjectId.get(),
                exam.totalScore(), exam.createdBy(), exam.completedBy()));
    }
}

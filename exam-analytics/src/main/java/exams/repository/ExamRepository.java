package exams.repository;

import exams.domain.Exam;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ExamRepository extends MongoRepository<Exam, ObjectId> {

    Optional<Exam> findExamById(int ExamId);
}

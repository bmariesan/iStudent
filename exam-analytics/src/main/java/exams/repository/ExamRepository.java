package exams.repository;

import exams.domain.Exam;
import exams.domain.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

//findAll e deja predefinit, l-am folosit
public interface ExamRepository extends MongoRepository<Exam, ObjectId> {

    Optional<Exam> findExamById(int ExamId);
}

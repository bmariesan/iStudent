package ro.ubb.istudent.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.domain.GreetingEntity;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.samples.architectural.mvc.student.model.Student;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<StudentEntity, ObjectId> {
    Optional<StudentEntity> findStudentEntityById(String studentId);
    Optional<StudentEntity> findStudentEntityByUsername(String username);
}

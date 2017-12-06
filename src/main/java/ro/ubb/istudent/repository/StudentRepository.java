package ro.ubb.istudent.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.domain.GreetingEntity;
import ro.ubb.istudent.domain.StudentEntity;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<StudentEntity, ObjectId> {
    Optional<StudentEntity> findStudentById(String studentId);
}

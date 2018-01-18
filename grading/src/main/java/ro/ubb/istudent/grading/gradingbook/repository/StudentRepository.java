package ro.ubb.istudent.grading.gradingbook.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.istudent.grading.gradingbook.domain.Student;
import ro.ubb.istudent.grading.gradingbook.domain.Teacher;

@Repository
public interface StudentRepository extends MongoRepository<Student, ObjectId> {
}

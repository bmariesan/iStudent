package exams.repository;

import exams.domain.ExamGrade;
import exams.domain.Gender;
import exams.domain.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student,ObjectId>{

    Optional<Student> findStudentById(int StudentId);
    List<Student> findAllByAge(int age);
    List<Student> findByAgeBetween(int from, int to);
    List<Student> findAllByGender(Gender gender);
    List<Student> findAllByCountry(String country);

}

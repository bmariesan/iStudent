package ro.ubb.istudent.grading.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.istudent.grading.gradingbook.Grade;

@Repository
public interface GradeRepository extends MongoRepository<Grade, ObjectId> {
}

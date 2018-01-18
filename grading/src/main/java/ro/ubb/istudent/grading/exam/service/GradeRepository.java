package ro.ubb.istudent.grading.exam.service;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.istudent.grading.gradingbook.domain.Grade;

@Repository
public interface GradeRepository extends MongoRepository<Grade, ObjectId> {
}

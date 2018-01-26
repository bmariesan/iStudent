package ro.ubb.istudent.grading.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.istudent.grading.gradingbook.Grade;
import ro.ubb.istudent.grading.gradingbook.SolidGrade;

import java.util.Optional;

@Repository
public interface GradeRepository extends MongoRepository<SolidGrade, ObjectId> {
    Optional<Grade> findById(final ObjectId id);
}

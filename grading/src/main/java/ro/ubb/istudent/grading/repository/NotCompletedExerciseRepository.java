package ro.ubb.istudent.grading.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.istudent.grading.domain.Exam;
import ro.ubb.istudent.grading.domain.NotCompletedExercise;

import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Repository
public interface NotCompletedExerciseRepository extends MongoRepository<NotCompletedExercise, ObjectId> {
    Optional<NotCompletedExercise> findById(final ObjectId id);
}

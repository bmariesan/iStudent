package ro.ubb.istudent.grading.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.istudent.grading.domain.ChoiceQuestion;
import ro.ubb.istudent.grading.domain.CompletedExercise;

import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Repository
public interface CompletedExecrciseRepository extends MongoRepository<CompletedExercise, ObjectId> {
    Optional<CompletedExercise> findById(final ObjectId id);
}

package ro.ubb.istudent.grading.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.domain.ChoiceQuestion;

import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Repository
public interface ChoiceQuestionRepository extends MongoRepository<ChoiceQuestion, ObjectId> {
    Optional<ChoiceQuestion> findById(final ObjectId id);
}

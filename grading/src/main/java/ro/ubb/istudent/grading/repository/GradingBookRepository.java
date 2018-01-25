package ro.ubb.istudent.grading.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.istudent.grading.gradingbook.GradingBook;
import ro.ubb.istudent.grading.gradingbook.SolidGradingBook;

import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Repository
public interface GradingBookRepository
        extends MongoRepository<SolidGradingBook, ObjectId> {
    Optional<GradingBook> findById(final ObjectId id);
}

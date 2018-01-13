package ro.ubb.istudent.grading.gradingbook;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GradingBookRepository extends MongoRepository<GradingBook, ObjectId> {
    Optional<GradingBook> findById(final ObjectId id);

}
package ro.ubb.istudent.grading.course;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Repository
public interface CourseRepository extends MongoRepository<Course, ObjectId> {
    Optional<Course> findById(final ObjectId id);
}

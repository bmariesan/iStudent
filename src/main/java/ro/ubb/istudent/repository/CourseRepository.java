package ro.ubb.istudent.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.domain.CourseEntity;
import ro.ubb.istudent.domain.NameEntity;

import java.util.Optional;

public interface CourseRepository extends MongoRepository<CourseEntity, ObjectId> {
    Optional<CourseEntity> findCourseEntityById(String courseId);
    Optional<CourseEntity> findCourseEntityByName(NameEntity name);
    Optional<CourseEntity> findCourseEntityByName(String name);
}

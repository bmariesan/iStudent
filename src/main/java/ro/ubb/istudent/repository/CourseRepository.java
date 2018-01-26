package ro.ubb.istudent.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ro.ubb.istudent.domain.CourseEntity;
import ro.ubb.istudent.domain.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends MongoRepository<CourseEntity,String>{ //replaced by string -ObjectId> by Valer

    Optional<CourseEntity> findByName(String courseName);
}

package ro.ubb.istudent.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.istudent.domain.TestEntity;

public interface TestRepository extends MongoRepository<TestEntity, ObjectId> {
}

package ro.ubb.istudent.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.domain.GreetingEntity;

import java.util.Optional;

public interface GreetingRepository extends MongoRepository<GreetingEntity, ObjectId> {

    Optional<GreetingEntity> findGreetingEntityById(String greetingId);
}

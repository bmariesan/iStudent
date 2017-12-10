package ro.ubb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.dto.DummyClass;

@Repository
public interface DummyRepo extends MongoRepository<DummyClass, Long> {
}

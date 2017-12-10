package main;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DummyRepo extends MongoRepository<DummyClass, Long> {
}

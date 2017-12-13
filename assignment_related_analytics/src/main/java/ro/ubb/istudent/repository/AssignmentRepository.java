package ro.ubb.istudent.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.domain.AssignmentEntity;

public interface AssignmentRepository extends MongoRepository<AssignmentEntity, Long> {
}

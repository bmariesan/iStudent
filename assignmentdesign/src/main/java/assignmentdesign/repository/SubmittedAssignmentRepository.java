package assignmentdesign.repository;

import assignmentdesign.domain.SubmittedAssignmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubmittedAssignmentRepository extends MongoRepository<SubmittedAssignmentEntity, Long> {

    <S extends SubmittedAssignmentEntity> S save(S entity);
}

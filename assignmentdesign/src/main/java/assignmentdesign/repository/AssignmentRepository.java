package assignmentdesign.repository;

import assignmentdesign.domain.AssignmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AssignmentRepository extends MongoRepository<AssignmentEntity, Long> {

    <S extends AssignmentEntity> S insert(S entity);

    List<AssignmentEntity> findAll();
}

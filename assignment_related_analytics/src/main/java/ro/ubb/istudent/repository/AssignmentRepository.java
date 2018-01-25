package ro.ubb.istudent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.istudent.domain.AssignmentEntity;

public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Long> {
}

package ro.ubb.istudent.repository;

import ro.ubb.istudent.domain.AssignmentEntity;

import java.util.List;

public interface AssignmentRepository {

    void save(AssignmentEntity assignmentEntity);
    void deleteAll();
    List<AssignmentEntity> findAll();

}

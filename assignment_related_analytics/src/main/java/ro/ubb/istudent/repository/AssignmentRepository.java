package ro.ubb.istudent.repository;

import ro.ubb.istudent.domain.AssignmentEntity;

import java.util.List;

public interface AssignmentRepository {

    void save(AssignmentEntity assignmentEntity);
    void deleteAll();
    void load(List<AssignmentEntity> l);
    List<AssignmentEntity> findAll();

}

package ro.ubb.istudent.repository;

import org.springframework.stereotype.Repository;
import ro.ubb.istudent.domain.AssignmentEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AssignmentRepositoryImpl implements AssignmentRepository {

    private List<AssignmentEntity> assignmentEntities;

    public AssignmentRepositoryImpl() {
        assignmentEntities = new ArrayList<>();
    }

    @Override
    public void save(AssignmentEntity assignmentEntity) {
        assignmentEntities.add(assignmentEntity);
    }

    @Override
    public void deleteAll() {
        assignmentEntities.clear();
    }

    @Override
    public void load(List<AssignmentEntity> l) {
        assignmentEntities = l;
    }

    @Override
    public List<AssignmentEntity> findAll() {
        return assignmentEntities;
    }
}

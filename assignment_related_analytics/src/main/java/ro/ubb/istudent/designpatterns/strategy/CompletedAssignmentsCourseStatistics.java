package ro.ubb.istudent.designpatterns.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.istudent.repository.AssignmentRepository;

@Service
@Transactional
public class CompletedAssignmentsCourseStatistics implements Strategy {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public long computeStatistics() {
        return assignmentRepository.findAll()
                .stream()
                .filter(assignmentEntity -> assignmentEntity.isCompleted())
                .count();
    }
}
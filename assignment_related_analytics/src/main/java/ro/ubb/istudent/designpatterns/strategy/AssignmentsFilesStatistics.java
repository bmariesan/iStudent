package ro.ubb.istudent.designpatterns.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import ro.ubb.istudent.repository.AssignmentRepository;

@Service
@Transactional
public class AssignmentsFilesStatistics implements Strategy {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public long computeStatistics() {
        return assignmentRepository.findAll()
                .stream()
                .filter(assignmentEntity -> !ObjectUtils.isEmpty(!assignmentEntity.getAttachments().isEmpty()))
                .count();
    }
}

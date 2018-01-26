package ro.ubb.istudent.designpatterns.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.istudent.domain.AssignmentEntity;
import ro.ubb.istudent.repository.AssignmentRepository;
import ro.ubb.istudent.service.AssignmentService;

import java.util.Comparator;

/**
 * Created by dariusi on 1/26/18.
 */
@Component("maxNrOfAttachementsPerAssignmentStatistics")
@Transactional
public class MaxNrOfAttachementsPerAssignmentStatistics implements Strategy {
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssignmentService assignmentService;

    @Override
    public long computeStatistics() {
        assignmentService.createMockData();
        // Define comparator
        Comparator<AssignmentEntity> comparator = (a1, a2) -> Integer.compare(a1.getAttachments().size(), a2.getAttachments().size());

        return assignmentRepository.findAll().stream().max(comparator).get().getAttachments().size();
    }
}

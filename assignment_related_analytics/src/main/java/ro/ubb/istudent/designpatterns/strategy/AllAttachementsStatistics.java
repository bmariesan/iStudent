package ro.ubb.istudent.designpatterns.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import ro.ubb.istudent.repository.AssignmentRepository;
import ro.ubb.istudent.service.AssignmentService;

/**
 * Created by dariusi on 1/26/18.
 */
@Component("allAttachementsStatistics")
@Transactional
public class AllAttachementsStatistics implements Strategy {
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssignmentService assignmentService;

    @Override
    public long computeStatistics() {
        assignmentService.createMockData();
        return this.assignmentRepository.findAll().
                stream().
                map(a -> a.getAttachments().size())
                .mapToInt(Integer::intValue)
                .sum();
    }
}

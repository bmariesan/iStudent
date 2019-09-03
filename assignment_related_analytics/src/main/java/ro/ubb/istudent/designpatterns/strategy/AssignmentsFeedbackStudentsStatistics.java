package ro.ubb.istudent.designpatterns.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import ro.ubb.istudent.repository.AssignmentRepository;
import ro.ubb.istudent.service.AssignmentService;

@Component("assignmentsFeedbackStudentsStatistics")
@Transactional
public class AssignmentsFeedbackStudentsStatistics implements Strategy {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssignmentService assignmentService;

    @Override
    public long computeStatistics() {
        assignmentService.createMockData();
        return this.assignmentRepository.findAll().stream().filter(
                assignmentEntity -> !ObjectUtils.isEmpty(assignmentEntity.getFeedback().getStudentEntity()))
                .count();
    }
}
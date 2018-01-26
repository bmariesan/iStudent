package ro.ubb.istudent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import ro.ubb.istudent.designpatterns.builder.AssignmentBuildDirector;
import ro.ubb.istudent.designpatterns.builder.AssignmentBuildImpl;
import ro.ubb.istudent.designpatterns.builder.AssignmentBuilder;
import ro.ubb.istudent.domain.AssignmentEntity;
import ro.ubb.istudent.repository.AssignmentRepository;
import ro.ubb.istudent.util.MockBuilder;

@Service
@Transactional
public class  AssignmentService {

    private static final Logger LOG = LoggerFactory.getLogger(AssignmentService.class);

    @Autowired
    private AssignmentRepository assignmentRepository;

    public Long getNumberOfAssignmentsHavingFeedbackFromTeachers() {
        return assignmentRepository.findAll()
                .stream()
                .filter(assignmentEntity -> !ObjectUtils.isEmpty(assignmentEntity.getFeedback().getTeacher()))
                .count();
    }

    public Long getNumberOfCompletedAssignmentsForEachCourse() {
        return assignmentRepository.findAll()
                .stream()
                .filter(assignmentEntity -> assignmentEntity.isCompleted())
                .count();
    }

    public Long getNumberOfAssignmentsHavingFilesAttached() {
        return assignmentRepository.findAll()
                .stream()
                .filter(assignmentEntity -> !ObjectUtils.isEmpty(!assignmentEntity.getAttachments().isEmpty()))
                .count();
    }

    public long getNrAssignWithFeedbackFromStudent() {
        return this.assignmentRepository.findAll().stream().filter(
                assignmentEntity -> !ObjectUtils.isEmpty(assignmentEntity.getFeedback().getStudentEntity()))
                .count();
    }

    public void createMockData() {
        assignmentRepository.load(MockBuilder.sharedInstace().assigns);
    }


}

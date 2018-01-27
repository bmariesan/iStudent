package assignmentdesign.service.submittedassignment;

import assignmentdesign.domain.SubmittedAssignmentEntity;
import assignmentdesign.dto.SubmittedAssignmentDto;
import assignmentdesign.repository.SubmittedAssignmentRepository;
import assignmentdesign.service.submittedassignment.mapper.SubmittedAssignmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubmittedAssignmentObject {

    private static final Logger log = LoggerFactory.getLogger(SubmittedAssignmentObject.class);

    @Autowired
    private SubmittedAssignmentRepository submittedAssignmentRepository;

    @Autowired
    private SubmittedAssignmentMapper submittedAssignmentMapper;

    public SubmittedAssignmentDto storeSubmittedAssignment(SubmittedAssignmentDto submittedAssignment, Integer assignmentId) {

        submittedAssignment.setAssignmentId(assignmentId);
        SubmittedAssignmentEntity submittedAssignmentEntity= submittedAssignmentMapper.map(submittedAssignment, SubmittedAssignmentEntity.class);
        SubmittedAssignmentEntity storedSubmittedAssignmentEntity = submittedAssignmentRepository.save(submittedAssignmentEntity);

        return submittedAssignmentMapper.map(storedSubmittedAssignmentEntity, SubmittedAssignmentDto.class);
    }
}

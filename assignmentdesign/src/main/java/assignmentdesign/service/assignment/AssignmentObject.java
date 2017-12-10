package assignmentdesign.service.assignment;

import assignmentdesign.domain.AssignmentEntity;
import assignmentdesign.dto.AssignmentDto;
import assignmentdesign.repository.AssignmentRepository;
import assignmentdesign.service.assignment.mapper.AssignmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssignmentObject {

    private static final Logger log = LoggerFactory.getLogger(AssignmentObject.class);

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssignmentMapper assignmentMapper;

    public AssignmentDto storeAssignment(AssignmentDto assignment) {

        AssignmentEntity assignmentEntity = assignmentMapper.map(assignment, AssignmentEntity.class);
        AssignmentEntity storedAssignmentEntity = assignmentRepository.insert(assignmentEntity);

        return assignmentMapper.map(storedAssignmentEntity, AssignmentDto.class);
    }

    public List<AssignmentDto> getAssignments() {

        List<AssignmentEntity> assignmentEntities = assignmentRepository.findAll();
        return assignmentMapper.mapAsList(assignmentEntities, AssignmentDto.class);
    }
}

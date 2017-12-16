package assignmentdesign.service.assignment;

import assignmentdesign.dto.AssignmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AssignmentSericeImpl implements AssignmentService {

    @Autowired
    private AssignmentObject assignmentObject;

    public AssignmentDto storeAssignment(AssignmentDto assignment) {
        return assignmentObject.storeAssignment(assignment);
    }

    public List<AssignmentDto> getAssignments() {
        return assignmentObject.getAssignments();
    }

    @Override
    public String uploadAttachment(Long assignmentId, String attachmentFilePath) throws IOException {
        return assignmentObject.uploadAttachment(assignmentId, attachmentFilePath);
    }


}

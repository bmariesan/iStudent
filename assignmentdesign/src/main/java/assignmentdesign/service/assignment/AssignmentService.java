package assignmentdesign.service.assignment;


import assignmentdesign.dto.AssignmentDto;

import java.io.IOException;
import java.util.List;

public interface AssignmentService {

    AssignmentDto storeAssignment(AssignmentDto assignment);

    List<AssignmentDto> getAssignments();

    String uploadAttachment(Long assignmentId, String attachmentFilePath) throws IOException;
}

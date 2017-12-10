package assignmentdesign.service.assignment;


import assignmentdesign.dto.AssignmentDto;

import java.util.List;

public interface AssignmentService {

    AssignmentDto storeAssignment(AssignmentDto assignment);

    List<AssignmentDto> getAssignments();
}

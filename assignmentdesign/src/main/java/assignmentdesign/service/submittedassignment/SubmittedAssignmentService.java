package assignmentdesign.service.submittedassignment;


import assignmentdesign.dto.SubmittedAssignmentDto;

public interface SubmittedAssignmentService {

    SubmittedAssignmentDto submitAssignment(SubmittedAssignmentDto submittedAssignment, Integer assignmentId);
}

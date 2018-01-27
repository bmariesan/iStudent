package assignmentdesign.service.submittedassignment;

import assignmentdesign.dto.SubmittedAssignmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmittedAssignmentSericeImpl implements SubmittedAssignmentService {

    @Autowired
    private SubmittedAssignmentObject submittedAssignmentObject;

    @Override
    public SubmittedAssignmentDto submitAssignment(SubmittedAssignmentDto submittedAssignment, Integer assignmentId) {
        return submittedAssignmentObject.storeSubmittedAssignment(submittedAssignment, assignmentId);
    }
}

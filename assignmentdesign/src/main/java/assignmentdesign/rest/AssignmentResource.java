package assignmentdesign.rest;

import assignmentdesign.dto.AssignmentDto;
import assignmentdesign.dto.SubmittedAssignmentDto;
import assignmentdesign.service.assignment.AssignmentService;
import assignmentdesign.service.submittedassignment.SubmittedAssignmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("/assignment")
@RestController
@Api(value = "assignment", description = "Endpoint for assignment management.")
public class AssignmentResource {

    private static final Logger log = LoggerFactory.getLogger(AssignmentResource.class);

    @Value("${application.base-url}")
    private String baseUrl;

    private static final String PATH_ASSIGNMENT = "/assignment";
    private static final String PATH_ATTACHMENT_UPLOAD = "/attachment-upload";
    private static final String PATH_SUBMIT_ASSIGNMENT = "/submit";

    private static final String PATH_PARAM_ASSIGNMENT_ID = "/{assignmentId}";

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private SubmittedAssignmentService submittedAssignmentService;

    @PostMapping
    @ApiOperation(value = "Add a new assignment")
    public ResponseEntity postAssignment(@RequestBody AssignmentDto assignment) throws URISyntaxException {

        log.info("Storing assignment with value: " + assignment);
        AssignmentDto storedAssignment = assignmentService.storeAssignment(assignment);
        return ResponseEntity.created(new URI(baseUrl + PATH_ASSIGNMENT + "/" + storedAssignment.getId())).build();
    }

    @GetMapping
    @ApiOperation(value = "Get all assignments")
    public ResponseEntity getAssignments() {

        log.info("Fetching assignments.");
        List<AssignmentDto> assignments = assignmentService.getAssignments();
        return ResponseEntity.ok(assignments);
    }

    @PostMapping(value = PATH_ATTACHMENT_UPLOAD + PATH_PARAM_ASSIGNMENT_ID)
    @ApiOperation(value = "Upload a file with the given file path to the assignment identified by the given id")
    public ResponseEntity postAssignmentAttachment(@PathVariable(value = "assignmentId") Long assignmentId,
                                                   @RequestBody String attachmentFilePath) throws IOException {

        log.info("Uploading attachment " + attachmentFilePath);
        String uploadedAttachmentFileName = assignmentService.uploadAttachment(assignmentId, attachmentFilePath);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = PATH_SUBMIT_ASSIGNMENT + PATH_PARAM_ASSIGNMENT_ID)
    @ApiOperation(value = "Submits a student's answers for an assignment")
    public ResponseEntity submitAssignment(@PathVariable(value = "assignmentId") Integer assignmentId,
                                           @RequestBody SubmittedAssignmentDto submittedAssignment) throws URISyntaxException {

        log.info("Submitting assignment");
        SubmittedAssignmentDto storedSubmittedAssignment = submittedAssignmentService.submitAssignment(submittedAssignment, assignmentId);
        return ResponseEntity.created(new URI(baseUrl + PATH_ASSIGNMENT + "/" + storedSubmittedAssignment.getId())).build();

    }
}

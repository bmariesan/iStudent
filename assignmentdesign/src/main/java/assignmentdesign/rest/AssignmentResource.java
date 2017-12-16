package assignmentdesign.rest;

import assignmentdesign.dto.AssignmentDto;
import assignmentdesign.service.assignment.AssignmentService;
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

@RequestMapping("/api")
@RestController
public class AssignmentResource {

    private static final Logger log = LoggerFactory.getLogger(AssignmentResource.class);

    @Value("${application.base-url}")
    private String baseUrl;

    private static final String PATH_ASSIGNMENT = "/assignment";
    private static final String PATH_ATTACHMENT_UPLOAD = "/attachment-upload";

    private static final String PATH_PARAM_ASSIGNMENT_ID = "/{assignmentId}";

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping(PATH_ASSIGNMENT)
    public ResponseEntity postAssignment(@RequestBody AssignmentDto assignment) throws URISyntaxException {

        log.info("Storing assignment with value: " + assignment);
        AssignmentDto storedAssignment = assignmentService.storeAssignment(assignment);
        return ResponseEntity.created(new URI(baseUrl + PATH_ASSIGNMENT + "/" + storedAssignment.getId())).build();
    }

    @GetMapping(PATH_ASSIGNMENT)
    public ResponseEntity getAssignments() {

        log.info("Fetching assignments.");
        List<AssignmentDto> assignments = assignmentService.getAssignments();
        return ResponseEntity.ok(assignments);
    }

    @PostMapping(value = PATH_ASSIGNMENT + PATH_ATTACHMENT_UPLOAD + PATH_PARAM_ASSIGNMENT_ID)
    public ResponseEntity postAssignmentAttachment(@PathVariable(value = "assignmentId") Long assignmentId,
                                                   @RequestBody String attachmentFilePath) throws IOException {

        log.info("Uploading attachment " + attachmentFilePath);
        String uploadedAttachmentFileName = assignmentService.uploadAttachment(assignmentId, attachmentFilePath);
        return new ResponseEntity(HttpStatus.OK);
    }
}

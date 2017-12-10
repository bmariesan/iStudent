package assignmentdesign.rest;

import assignmentdesign.dto.AssignmentDto;
import assignmentdesign.service.assignment.AssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

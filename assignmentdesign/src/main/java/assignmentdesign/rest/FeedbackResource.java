package assignmentdesign.rest;

import assignmentdesign.dto.FeedbackDto;
import assignmentdesign.service.feedback.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by ale_mi26 on 12/12/2017.
 */
@RequestMapping("/feedback")
@RestController
@Api(value = "feedback", description = "Endpoint for feedback management")
public class FeedbackResource {

    private static final Logger log = LoggerFactory.getLogger(FeedbackResource.class);

    @Value("${application.base-url}")
    private String baseUrl;

    private static final String PATH_PARAM_ASSIGNMENT_ID = "/{assignmentId}";

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping(PATH_PARAM_ASSIGNMENT_ID)
    @ApiOperation(value = "Add feedback for the assignment identified by id")
    public ResponseEntity postFeedback(@PathVariable("assignmentId") Integer assignmentId,@RequestBody FeedbackDto feedbackDto) throws URISyntaxException {

        log.info("Storing feedback with value: " + feedbackDto);
        FeedbackDto storedFeedback= feedbackService.storeFeedback(assignmentId,feedbackDto);

        return ResponseEntity.created(new URI(baseUrl + "/" + storedFeedback.getId())).build();
    }

    @GetMapping
    @ApiOperation(value = "Get all feedback")
    public ResponseEntity getFeedbacks() {

        log.info("Fetching feedbacks.");
        List<FeedbackDto> feedbacks= feedbackService.getFeedback();
        return ResponseEntity.ok(feedbacks);
    }


}

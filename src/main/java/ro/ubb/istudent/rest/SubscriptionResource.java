package ro.ubb.istudent.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.service.SubscriptionService;

/**
 * Created on 09.01.2018.
 */
@RequestMapping("/api")
@RestController
public class SubscriptionResource {
    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);

    private final SubscriptionService subscriptionService;

    SubscriptionResource(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscribe/{username}")
    public ResponseEntity<String> attendCourse(
            @PathVariable("username") String username, @RequestBody CourseDto course) {
        subscriptionService.subscribeStudentToCourse(username, course);
        //TODO figure out what is the best response for this request.
        return new ResponseEntity<>("Registered user to the course!", HttpStatus.OK);
    }
}

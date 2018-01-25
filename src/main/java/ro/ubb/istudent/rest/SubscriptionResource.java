package ro.ubb.istudent.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.domain.NameEntity;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.exception.EntityNotFoundException;
import ro.ubb.istudent.service.SubscriptionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        subscriptionService.subscribeStudentToCourse("dana", CourseDto.builder().name(new NameEntity("Algebra")).studentLimit(10).active(false).build());
    }

    @PostMapping("/subscribe/{username}")
    public ResponseEntity<StudentDto> attendCourse(
            @PathVariable("username") String username, @RequestBody CourseDto course) {
        StudentDto student = subscriptionService.subscribeStudentToCourse(username, course);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/subscribe/courses/available/{username}")
    public ResponseEntity<List<CourseDto>> getAvailableCoursesForStudent(@PathVariable("username") String username) {
        List<CourseDto> availableCourses = subscriptionService.getAvailableCoursesForStudent(username);
        return new ResponseEntity<>(availableCourses, HttpStatus.OK);
    }

    @GetMapping("/students/{username}/courses")
    public ResponseEntity<List<CourseDto>> getCoursesOfStudent(@PathVariable String username) {
        try {
            List<CourseDto> registeredCourses = subscriptionService.getRegisteredCoursesForStudent(username);
            return new ResponseEntity<>(registeredCourses, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }
}

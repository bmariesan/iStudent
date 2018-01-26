package ro.ubb.istudent.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.domain.NameEntity;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.dto.CourseworkDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.exception.EntityNotFoundException;
import ro.ubb.istudent.service.CourseworkService;
import ro.ubb.istudent.service.SubscriptionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 09.01.2018.
 */
@RequestMapping("/api")
@RestController
public class SubscriptionResource {
    private final SubscriptionService subscriptionService;
    private final CourseworkService courseworkService;

    SubscriptionResource(SubscriptionService subscriptionService, CourseworkService courseworkService) {
        this.subscriptionService = subscriptionService;
        this.courseworkService = courseworkService;
        CourseDto course = CourseDto.builder()
                .name(new NameEntity("Algebra"))
                .studentLimit(10)
                .active(false)
                .build();
        subscriptionService.subscribeStudentToCourse("dana", course);
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

    @PostMapping("/coursework/completed/assignments/{username}")
    public ResponseEntity<List<CourseworkDto>> getCompletedAssignments(
            @PathVariable String username, @RequestBody CourseDto course) {
        List<CourseworkDto> assignments = courseworkService.getCompletedAssignmentsForCourse(username, course);
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    @PostMapping("/coursework/left/assignments/{username}")
    public ResponseEntity<List<CourseworkDto>> getLeftAssignments(
            @PathVariable String username, @RequestBody CourseDto course) {
        List<CourseworkDto> assignments = courseworkService.getLeftAssignmentsForCourse(username, course);
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    @PostMapping("/coursework/completed/exams/{username}")
    public ResponseEntity<List<CourseworkDto>> getCompletedExams(
            @PathVariable String username, @RequestBody CourseDto course) {
        List<CourseworkDto> exams = courseworkService.getCompletedExamsForCourse(username, course);
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @PostMapping("/coursework/left/exams/{username}")
    public ResponseEntity<List<CourseworkDto>> getLeftExams
            (@PathVariable String username, @RequestBody CourseDto course) {
        List<CourseworkDto> exams = courseworkService.getLeftExamsForCourse(username, course);
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @PostMapping("/coursework/progress/{username}")
    public ResponseEntity<Integer> getProgress(@PathVariable String username, @RequestBody CourseDto courseDto) {
        int percentage = courseworkService.getOverallProgress(username, courseDto);
        return new ResponseEntity<>(percentage, HttpStatus.OK);
    }
}

package ro.ubb.istudent.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.exception.EntityNotFoundException;
import ro.ubb.istudent.service.CourseService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CourseResource {
    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);

    private final CourseService service;
    private final String baseUrl;

    public CourseResource(CourseService service, @Value("${application.base-url}") String baseUrl) {
        this.service = service;
        this.baseUrl = baseUrl;
        service.createCourse(CourseDto.builder().name("Design Patterns").studentLimit(1).build());
        service.createCourse(CourseDto.builder().name("Algebra").studentLimit(10).build());
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = service.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping("/courses/{name}/setlimit/{limit}")
    public ResponseEntity<Void> setStudentLimitToCourse(@PathVariable String name, @PathVariable int limit) {
        try {
            service.setLimitToCourse(name, limit);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }
}

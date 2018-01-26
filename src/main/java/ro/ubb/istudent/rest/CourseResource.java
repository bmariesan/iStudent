package ro.ubb.istudent.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.domain.NameEntity;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.service.CourseService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CourseResource {
    private final CourseService service;

    public CourseResource(CourseService service) {
        this.service = service;
        service.createCourse(CourseDto.builder().name(new NameEntity("Design Patterns")).studentLimit(1).active(true).build());
        service.createCourse(CourseDto.builder().name(new NameEntity("Algebra")).studentLimit(10).active(false).build());
        service.createCourse(CourseDto.builder().name(new NameEntity("Databases")).studentLimit(5).active(false).build());
        service.createCourse(CourseDto.builder().name(new NameEntity("Cryptography")).studentLimit(5).active(true).build());
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = service.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}

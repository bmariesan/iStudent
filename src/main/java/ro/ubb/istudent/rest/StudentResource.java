package ro.ubb.istudent.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.service.CourseService;
import ro.ubb.istudent.service.StudentService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class StudentResource {
    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);
    private final StudentService service;
    private final String baseUrl;

    public StudentResource(StudentService service, @Value("${application.base-url}") String baseUrl) {
        this.service = service;
        this.baseUrl = baseUrl;
        service.createStudent(StudentDto.builder().name("test1").build());
        service.createStudent(StudentDto.builder().name("test2").build());
    }

    @GetMapping("/students")
    public ResponseEntity getAllStudents() {
        List<StudentDto> students = service.findAll();
        return new ResponseEntity<List<StudentDto>>(students, HttpStatus.OK);
    }
}

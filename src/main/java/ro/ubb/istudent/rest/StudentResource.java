package ro.ubb.istudent.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.service.StudentService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class StudentResource {
    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);

    private final StudentService studentService;
    private final String baseUrl;

    public StudentResource(StudentService studentService, @Value("${application.base-url}") String baseUrl) {
        this.studentService = studentService;
        this.baseUrl = baseUrl;
        studentService.createStudent(StudentDto.builder().username("dana").name("Dana").build());
        studentService.createStudent(StudentDto.builder().username("bianca").name("Bianca").build());
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


}

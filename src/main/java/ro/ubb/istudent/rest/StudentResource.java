package ro.ubb.istudent.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.domain.NameEntity;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.service.StudentService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class StudentResource {
    private final StudentService studentService;

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
        studentService.createStudent(StudentDto.builder().username("dana").name(new NameEntity("Dana")).build());
        studentService.createStudent(StudentDto.builder().username("bianca").name(new NameEntity("Bianca")).build());
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


}

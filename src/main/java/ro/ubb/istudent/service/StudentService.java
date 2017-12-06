package ro.ubb.istudent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.CourseEntity;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.dto.GreetingDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.repository.CourseRepository;
import ro.ubb.istudent.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    private StudentDto studentToStudentDTO(StudentEntity entity) {
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
    private StudentEntity studentDTOToStudent(StudentDto student) {
        StudentEntity studentEntity = StudentEntity.builder()
                .name(student.getName())
                .build();
        return studentEntity;
    }
    public Optional<StudentDto> findStudentById(String studentId) {
        return repository.findStudentById(studentId)
                .map(this::studentToStudentDTO);
    }

    public StudentDto createStudent(StudentDto student) {
        return studentToStudentDTO(repository.save(studentDTOToStudent(student)));
    }

    public List<StudentDto> findAll() {
        return createFromEntities(repository.findAll());
    }

    private List<StudentDto> createFromEntities(List<StudentEntity> entities) {
        return entities.stream()
                .map(this::studentToStudentDTO)
                .collect(Collectors.toList());
    }

}

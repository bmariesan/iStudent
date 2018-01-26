package ro.ubb.istudent.service;

import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.exception.EntityNotFoundException;
import ro.ubb.istudent.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public StudentDto createStudent(StudentDto student) {
        return StudentDto.createDtoFromEntity(repository.save(StudentDto.createEntityFromDto(student)));
    }

    public List<StudentDto> findAll() {
        return StudentDto.createDtosFromEntities(repository.findAll());
    }

    StudentEntity getStudentWithUsername(String username) {
        Optional<StudentEntity> studentOptional = repository.findStudentEntityByUsername(username);
        if (!studentOptional.isPresent()) {
            throw new EntityNotFoundException("A student with the username " + username + " was not found!");
        }
        return studentOptional.get();
    }
}

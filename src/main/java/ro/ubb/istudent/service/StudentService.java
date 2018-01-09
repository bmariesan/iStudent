package ro.ubb.istudent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Optional<StudentDto> findStudentById(String studentId) {
        return repository.findStudentEntityById(studentId)
                .map(StudentDto::createDtoFromEntity);
    }

    public StudentDto createStudent(StudentDto student) {
        return StudentDto.createDtoFromEntity(repository.save(StudentDto.createEntityFromDto(student)));
    }

    public List<StudentDto> findAll() {
        return StudentDto.createDtosFromEntities(repository.findAll());
    }


}

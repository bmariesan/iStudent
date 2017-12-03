package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.converters.CountryConverter;
import ro.ubb.istudent.converters.StudentConverter;
import ro.ubb.istudent.dto.CountryDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.enums.Gender;
import ro.ubb.istudent.repository.StudentRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class StudentService {

    private StudentRepository repository;

    private StudentConverter studentConverter;

    private CountryConverter countryConverter;

    public StudentDto save(StudentDto studentDto) {
        return studentConverter.createFromEntity(repository.save(studentConverter.createFromDto(studentDto)));
    }

    public List<StudentDto> findAll() {
        return studentConverter.createFromEntities(repository.findAll());
    }

    public Optional<StudentDto> findByName(String name) {
        return repository.findByName(name)
                .map(studentConverter::createFromEntity);
    }

    public List<StudentDto> findByCountry(CountryDto countryDto) {
        return repository.findAllByCountry(countryConverter.createFromDto(countryDto))
                .map(studentConverter::createFromEntities)
                .orElse(Collections.emptyList());
    }

    public Optional<List<StudentDto>> findByGender(Gender gender) {
        return repository.findAllByGender(gender)
                .map(studentConverter::createFromEntities);
    }

    public Optional<StudentDto> update(StudentDto studentDto) {
        return repository.findByName(studentDto.getName())
                .map(studentEntity -> studentConverter.updateEntity(studentEntity, studentDto))
                .map(repository::save)
                .map(studentConverter::createFromEntity);
    }

}

package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.converters.CountryConverter;
import ro.ubb.istudent.converters.StudentConverter;
import ro.ubb.istudent.dto.AgeDto;
import ro.ubb.istudent.dto.CountryDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.enums.GenderEnum;
import ro.ubb.istudent.repository.StudentRepository;

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

    public Optional<List<StudentDto>> findByCountry(CountryDto countryDto) {
        return repository.findAllByCountry(countryConverter.createFromDto(countryDto))
                .map(studentConverter::createFromEntities);
    }

    public Optional<List<StudentDto>> findByGender(GenderEnum gender) {
        return repository.findAllByGender(gender)
                .map(studentConverter::createFromEntities);
    }

    public Optional<List<StudentDto>> findByAgeBetween(AgeDto ageDto) {
        return repository.findAllByAgeBetween(ageDto.getMinAge(), ageDto.getMaxAge())
                .map(studentConverter::createFromEntities);
    }

    public Optional<List<StudentDto>> findByAgeGreaterThanOrEqual(Integer age) {
        return repository.findAllByAgeGreaterThanEqual(age)
                .map(studentConverter::createFromEntities);
    }

    public Optional<StudentDto> update(StudentDto studentDto) {
        return repository.findByName(studentDto.getName())
                .map(studentEntity -> studentConverter.updateEntity(studentEntity, studentDto))
                .map(repository::save)
                .map(studentConverter::createFromEntity);
    }

}

package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.converters.CountryConverter;
import ro.ubb.istudent.converters.StudentConverter;
import ro.ubb.istudent.converters.strategy2.ConverterStrategy;
import ro.ubb.istudent.domain.BaseEntity;
import ro.ubb.istudent.domain.CountryEntity;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.istudent.dto.AgeDto;
import ro.ubb.istudent.dto.BaseDto;
import ro.ubb.istudent.dto.CountryDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.enums.GenderEnum;
import ro.ubb.istudent.repository.StudentRepository;
import ro.ubb.samples.architectural.mvc.student.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class StudentService {

    private StudentRepository repository;

    private ConverterStrategy converterStrategy;

    public StudentDto save(StudentDto studentDto) {
        return (StudentDto) converterStrategy
                .createFromEntity(repository.save((StudentEntity) converterStrategy.createFromDto(studentDto)));
    }

    public List<StudentDto> findAll() {
        return converterStrategy.createFromEntities(repository.findAll())
                .stream().map(t -> (StudentDto) t).collect(Collectors.toList());
    }

    public Optional<StudentDto> findByName(String name) {
        return repository.findByName(name)
                .map(s -> (StudentDto) converterStrategy.createFromEntity(s));
    }

    public Optional<List<StudentDto>> findByCountry(CountryDto countryDto) {
        return convertStudentEntitiesToDtos(
                repository.findAllByCountry((CountryEntity) converterStrategy.createFromDto(countryDto))
        );
    }

    public Optional<List<StudentDto>> findByGender(GenderEnum gender) {
        return convertStudentEntitiesToDtos(repository.findAllByGender(gender));
    }

    public Optional<List<StudentDto>> findByAgeBetween(AgeDto ageDto) {
        return convertStudentEntitiesToDtos(repository.findAllByAgeBetween(ageDto.getMinAge(), ageDto.getMaxAge()));
    }

    public Optional<List<StudentDto>> findByAgeGreaterThanOrEqual(Integer age) {
        return convertStudentEntitiesToDtos(repository.findAllByAgeGreaterThanEqual(age));
    }

    public Optional<StudentDto> update(StudentDto studentDto) {
        return repository.findByName(studentDto.getName())
                .map(studentEntity -> (StudentEntity)converterStrategy.updateEntity(studentEntity, studentDto))
                .map(repository::save)
                .map(studentEntity -> (StudentDto) converterStrategy.createFromEntity(studentEntity));
    }

    private Optional<List<StudentDto>> convertStudentEntitiesToDtos(Optional<List<StudentEntity>> optional){
        return optional.map(studentEntities ->
                studentEntities.stream()
                        .map(stEntity -> (StudentDto)converterStrategy.createFromEntity(stEntity))
                        .collect(Collectors.toList())
        );
    }

}

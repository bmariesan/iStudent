package ro.ubb.istudent.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.istudent.dto.StudentDto;

import java.util.Collections;

@Component
public class StudentConverter extends GenericConverter<StudentEntity, StudentDto> {

    @Autowired
    private TestConverter testConverter;

    @Autowired
    private CountryConverter countryConverter;

    @Override
    public StudentEntity createFromDto(StudentDto dto) {
        StudentEntity studentEntity = StudentEntity.builder()
                .age(dto.getAge())
                .gender(dto.getGender())
                .name(dto.getName())
                .country(countryConverter.createFromDto(dto.getCountryDto()))
                .tests(testConverter.createFromDtos(dto.getTests() != null ? dto.getTests() : Collections.emptyList()))
                .build();
        studentEntity.setId(dto.getId());

        return studentEntity;
    }

    @Override
    public StudentDto createFromEntity(StudentEntity entity) {
        StudentDto studentDto = StudentDto.builder()
                .age(entity.getAge())
                .gender(entity.getGender())
                .name(entity.getName())
                .countryDto(countryConverter.createFromEntity(entity.getCountry()))
                .tests(testConverter.createFromEntities(entity.getTests() != null ? entity.getTests() : Collections.emptyList()))
                .build();
        studentDto.setId(entity.getId());

        return studentDto;
    }

    @Override
    public StudentEntity updateEntity(StudentEntity entity, StudentDto dto) {
        entity.setAge(dto.getAge());
        entity.setName(dto.getName());
        entity.setGender(dto.getGender());
        entity.setTests(testConverter.createFromDtos(dto.getTests()));

        return entity;
    }
}

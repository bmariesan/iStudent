package ro.ubb.istudent.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.ubb.istudent.domain.CourseEntity;
import ro.ubb.istudent.domain.TestEntity;
import ro.ubb.istudent.dto.TestDto;

import java.util.List;

@Component
public class TestConverter extends GenericConverter<TestEntity, TestDto> {

    @Autowired
    private CourseConverter courseConverter;

    @Override
    public TestEntity createFromDto(TestDto dto) {
        return TestEntity.builder()
                .grade(dto.getGrade())
                .course(courseConverter.createFromDto(dto.getCourseDto()))
                .build();
    }

    @Override
    public TestDto createFromEntity(TestEntity entity) {
        return TestDto.builder()
                .grade(entity.getGrade())
                .courseDto(courseConverter.createFromEntity(entity.getCourse()))
                .build();
    }

    @Override
    public TestEntity updateEntity(TestEntity entity, TestDto dto) {
        entity.setCourse(courseConverter.createFromDto(dto.getCourseDto()));
        entity.setGrade(dto.getGrade());

        return entity;
    }
}

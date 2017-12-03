package ro.ubb.istudent.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.istudent.domain.CourseEntity;
import ro.ubb.istudent.dto.CourseDto;

import java.util.Collections;

@Component
public class CourseConverter extends GenericConverter<CourseEntity, CourseDto> {

    @Autowired
    private StudentConverter studentConverter;

    @Override
    public CourseEntity createFromDto(CourseDto dto) {
        CourseEntity courseEntity = CourseEntity.builder()
                .name(dto.getName())
                .minimumGrade(dto.getMinimumGrade())
                .build();
        courseEntity.setId(dto.getId());

        return courseEntity;
    }

    @Override
    public CourseDto createFromEntity(CourseEntity entity) {
        CourseDto courseDto = CourseDto.builder()
                .name(entity.getName())
                .minimumGrade(entity.getMinimumGrade())
                .build();
        courseDto.setId(entity.getId());

        return courseDto;
    }

    @Override
    public CourseEntity updateEntity(CourseEntity entity, CourseDto dto) {
        entity.setName(dto.getName());
        entity.setMinimumGrade(dto.getMinimumGrade());

        return entity;
    }
}

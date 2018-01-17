package ro.ubb.istudent.dto;

import lombok.*;
import ro.ubb.istudent.domain.CourseEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto extends BaseDto {
    private String name;
    private Integer numRegisteredStudents;
    private Integer studentLimit;
    private boolean active;

    public static CourseDto createDtoFromEntity(CourseEntity entity) {
        CourseDto courseDto = CourseDto.builder()
                .name(entity.getName())
                .studentLimit(entity.getStudentLimit())
                .numRegisteredStudents(entity.getRegisteredStudents().size())
                .active(entity.isActive())
                .build();
        courseDto.setId(entity.getId());

        return courseDto;
    }

    public static CourseEntity createEntityFromDto(CourseDto dto) {
        CourseEntity courseEntity = CourseEntity.builder()
                .name(dto.getName())
                .studentLimit(dto.getStudentLimit())
                .active(dto.isActive())
                .build();
        courseEntity.setId(dto.getId());

        return courseEntity;
    }

    public static List<CourseDto> createDtosFromEntities(List<CourseEntity> entities) {
        return entities.stream()
                .peek(course -> {
                    System.out.println(course);
                    course.getRegisteredStudents().forEach(student -> System.out.println(student.getName()));
                })
                .map(CourseDto::createDtoFromEntity)
                .collect(Collectors.toList());
    }

}

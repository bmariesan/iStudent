package ro.ubb.istudent.dto;

import lombok.*;
import ro.ubb.istudent.domain.NameEntity;
import ro.ubb.istudent.domain.StudentEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto extends BaseDto {
    private String username;
    private NameEntity name;
    private List<CourseDto> registeredCourses;

    public static StudentDto createDtoFromEntity(StudentEntity entity) {
        List<CourseDto> courseDtos = CourseDto.createDtosFromEntities(entity.getRegisteredCourses());
        StudentDto dto = StudentDto.builder()
                .name(entity.getName())
                .username(entity.getUsername())
                .registeredCourses(courseDtos)
                .build();
        dto.setId(entity.getId());
        return dto;
    }

    public static StudentEntity createEntityFromDto(StudentDto student) {
        return StudentEntity.builder()
                .name(student.getName())
                .username(student.getUsername())
                .build();
    }

    public static List<StudentDto> createDtosFromEntities(List<StudentEntity> entities) {
        return entities.stream()
                .peek(student -> {
                    System.out.println(student);
                    student.getRegisteredCourses().forEach(course -> System.out.println(course.getName()));
                })
                .map(StudentDto::createDtoFromEntity)
                .collect(Collectors.toList());
    }
}

package ro.ubb.istudent.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TestDto extends BaseDto {
    private CourseDto courseDto;

    private Integer grade;
}

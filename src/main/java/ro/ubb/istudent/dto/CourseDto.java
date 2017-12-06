package ro.ubb.istudent.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto extends BaseDto {
    private String name;

    private Integer studentLimit;
}

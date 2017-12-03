package ro.ubb.istudent.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto extends BaseDto {

    private String name;

    private Integer minimumGrade;
}

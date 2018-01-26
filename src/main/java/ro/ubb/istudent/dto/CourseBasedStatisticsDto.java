package ro.ubb.istudent.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CourseBasedStatisticsDto extends BaseDto { //+Valer

    private String courseName;

    private Integer graduatedStudentsNumber;

    private Integer totalNumberOfStudents;

    private List<StudentDto> graduatedStudents;
}

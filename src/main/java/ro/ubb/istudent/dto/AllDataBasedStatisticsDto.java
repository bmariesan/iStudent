package ro.ubb.istudent.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class AllDataBasedStatisticsDto extends BaseDto {

    private Integer totalNumberOfStudents;

    private List<StudentDto> allStudents;
}

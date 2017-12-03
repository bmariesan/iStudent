package ro.ubb.istudent.dto;

import lombok.*;
import ro.ubb.istudent.enums.Gender;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class GenderBasedStatisticsDto extends BaseDto {
    private Gender gender;

    private List<StudentDto> graduatedStudents;
}

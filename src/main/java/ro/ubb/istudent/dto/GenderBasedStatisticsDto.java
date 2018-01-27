package ro.ubb.istudent.dto;

import lombok.*;
import ro.ubb.istudent.enums.GenderEnum;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class GenderBasedStatisticsDto extends BaseDto {
    private GenderEnum gender;

    private List<StudentDto> graduatedStudents;
}

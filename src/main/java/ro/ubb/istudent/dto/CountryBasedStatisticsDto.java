package ro.ubb.istudent.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CountryBasedStatisticsDto extends BaseDto {
    private CountryDto country;

    private List<StudentDto> graduatedStudents;
}

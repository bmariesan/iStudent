package ro.ubb.istudent.dto;

import lombok.*;
import ro.ubb.istudent.enums.Gender;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto extends BaseDto {

    private String name;

    private Gender gender;

    private Integer age;

    private List<TestDto> tests = new ArrayList<>();

    private CountryDto countryDto;
}

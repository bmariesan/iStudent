package ro.ubb.istudent.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto extends BaseDto {

    private String countryName;

}

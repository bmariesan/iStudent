package ro.ubb.istudent.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class AgeDto extends BaseDto{
    private Integer minAge;

    private Integer maxAge;
}

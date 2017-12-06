package ro.ubb.istudent.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto extends BaseDto{
    private String name;
}

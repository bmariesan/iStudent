package assignmentdesign.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SubmittedAssignmentDto {

    private Long id;
    private Integer studentId;
    private Integer assignmentId;
    //<task id, answer>
    private Map<Integer, String> answers;
}

package assignmentdesign.dto;

import assignmentdesign.domain.Task;
import lombok.Data;

import java.util.List;

@Data
public class AssignmentDto {

    private Integer id;

    private Boolean isCompulsory;

    private Integer points;

    private List<String> attachmentPaths;

    private List<String> tips;

    private Integer idFeedback;

    private List<Task> tasks;
}

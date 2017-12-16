package assignmentdesign.dto;

import lombok.Data;

import java.util.List;

@Data
public class AssignmentDto {

    private Integer id;

    private Boolean isCompulsory;

    private Integer points;

    private String type;

    private List<String> attachmentPaths;

    private List<String> tips;

    private Integer idFeedback;
}

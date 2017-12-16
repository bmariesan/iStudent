package assignmentdesign.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "assignment")
public class AssignmentEntity implements Serializable {

    @Id
    private Integer id;

    private Boolean isCompulsory;

    private Integer points;

    private String type;

    private List<String> attachmentPaths;

    private List<String> tips;

    private Integer idFeedback;
}

package assignmentdesign.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "assignment")
public class AssignmentEntity implements Serializable {

    @Id
    private Integer id;

    private Boolean isCompulsory;

    private Integer points;

    private List<String> attachmentPaths;

    private List<String> tips;

    private Integer idFeedback;

    private List<Task> tasks;
}

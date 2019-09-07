package assignmentdesign.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@AllArgsConstructor
@Document(collection = "submitted_assignment")
public class SubmittedAssignmentEntity {

    @Id
    private Long id;
    private Integer studentId;
    private Integer assignmentId;
    private Map<Integer, String> answers;
}

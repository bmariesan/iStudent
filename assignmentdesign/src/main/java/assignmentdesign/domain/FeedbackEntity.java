package assignmentdesign.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by ale_mi26 on 12/12/2017.
 */
@Data
@AllArgsConstructor
@Document(collection = "feedback")
public class FeedbackEntity implements Serializable{

    @Id
    private Integer id;

    private String writtenByType;
    private Integer writtenById;
    private String description;
}

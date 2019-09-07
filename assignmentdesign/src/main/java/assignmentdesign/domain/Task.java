package assignmentdesign.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Data
public class Task {

    @Id
    private Long id;
    private int type;
    private String description;
    private Map<String, String> multipleChoices;
}

package assignmentdesign.dto;

import lombok.Data;

/**
 * Created by ale_mi26 on 12/12/2017.
 */
@Data
public class FeedbackDto {

    private Integer id;
    private String writtenByType;
    private Integer writtenById;
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWrittenByType() {
        return writtenByType;
    }

    public void setWrittenByType(String writtenByType) {
        this.writtenByType = writtenByType;
    }

    public Integer getWrittenById() {
        return writtenById;
    }

    public void setWrittenById(Integer writtenById) {
        this.writtenById = writtenById;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

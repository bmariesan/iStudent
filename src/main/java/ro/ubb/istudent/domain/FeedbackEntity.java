package ro.ubb.istudent.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

//@Document(collection = "feedback")
@Entity
public class FeedbackEntity implements Serializable{

    @Id
    private Long id;
    private TeacherEntity teacher;
    private Long studentId;
    private String description;

    public FeedbackEntity(Long id, TeacherEntity teacher, Long studentId, String description) {
        this.id = id;
        this.teacher = teacher;
        this.studentId = studentId;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

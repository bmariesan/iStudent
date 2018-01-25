package ro.ubb.istudent.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class FeedbackEntity implements Serializable {

    @Id
    private Long id;
    private TeacherEntity teacher;
    private Long studentId;
    private String description;
    private StudentEntity studentEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSIGNMENT_ID")
    private AssignmentEntity assignment;

    public FeedbackEntity(Long id, TeacherEntity teacher, Long studentId, String description, StudentEntity studentEntity,
                          AssignmentEntity assignment) {
        this.id = id;
        this.teacher = teacher;
        this.studentId = studentId;
        this.description = description;
        this.studentEntity = studentEntity;
        this.assignment = assignment;
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

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public AssignmentEntity getAssignment() {
        return assignment;
    }

    public void setAssignment(AssignmentEntity assignment) {
        this.assignment = assignment;
    }
}

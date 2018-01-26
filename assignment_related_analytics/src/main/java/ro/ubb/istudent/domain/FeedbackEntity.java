package ro.ubb.istudent.domain;

import java.io.Serializable;


public class FeedbackEntity implements Serializable {

    private Integer id;
    private TeacherEntity teacher;
    private String description;
    private StudentEntity studentEntity;

    private AssignmentEntity assignment;

    public FeedbackEntity(Integer id, TeacherEntity teacher, String description, StudentEntity studentEntity,
                          AssignmentEntity assignment) {
        this.id = id;
        this.teacher = teacher;
        this.description = description;
        this.studentEntity = studentEntity;
        this.assignment = assignment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {this.id = id;}

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
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

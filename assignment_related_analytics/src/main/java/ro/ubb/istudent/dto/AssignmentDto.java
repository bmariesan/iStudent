package ro.ubb.istudent.dto;

import java.util.Date;

public class AssignmentDto implements Dto {

    private Long id;
    private Long studentId;
    private Long courseId;
    private Long materialId;
    private Long feedbackId;
    private Date date;
    private Date deadline;
    private String description;

    public AssignmentDto(Long id, Long studentId, Long courseId, Long materialId, Long feedbackId, Date date, Date deadline, String description) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.materialId = materialId;
        this.feedbackId = feedbackId;
        this.date = date;
        this.deadline = deadline;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

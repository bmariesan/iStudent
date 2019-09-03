//package ro.ubb.istudent.domain;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//public class AttachmentEntity implements Serializable {
//
//    @Id
//    private Long id;
//    private String content;
//
//    @ManyToOne(fetch= FetchType.LAZY)
//    @JoinColumn(name="ASSIGNMENT_ID")
//    private AssignmentEntity assignment;
//
//    public AttachmentEntity(Long id, String content, AssignmentEntity assignment) {
//        this.id = id;
//        this.content = content;
//        this.assignment = assignment;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public AssignmentEntity getAssignment() {
//        return assignment;
//    }
//
//    public void setAssignment(AssignmentEntity assignment) {
//        this.assignment = assignment;
//    }
//}

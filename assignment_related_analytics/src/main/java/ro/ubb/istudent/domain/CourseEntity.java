//package ro.ubb.istudent.domain;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//public class CourseEntity implements Serializable {
//
//    @Id
//    private Long id;
//    private String name;
//    private Long teacherID;
//    private List<Long> materials;
//    private List<Long> students;
//    private List<Long> assignements;
//    private List<Long> exams;
//    private Long yearOfStudy;
//    private Date startDate;
//    private Date endDate;
//    private Integer minimumCourseMaterials;
//
//    public CourseEntity(Long id,
//                        String name,
//                        Long teacherID,
//                        Long yearOfStudy,
//                        Date startDate,
//                        Date endDate,
//                        Integer minimumCourseMaterials) {
//        this.id = id;
//        this.name = name;
//        this.teacherID = teacherID;
//        this.yearOfStudy = yearOfStudy;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.minimumCourseMaterials = minimumCourseMaterials;
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Long getTeacherID() {
//        return teacherID;
//    }
//
//    public void setTeacherID(Long teacherID) {
//        this.teacherID = teacherID;
//    }
//
//    public List<Long> getMaterials() {
//        return materials;
//    }
//
//    public void setMaterials(List<Long> materials) {
//        this.materials = materials;
//    }
//
//    public List<Long> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<Long> students) {
//        this.students = students;
//    }
//
//    public List<Long> getAssignements() {
//        return assignements;
//    }
//
//    public void setAssignements(List<Long> assignements) {
//        this.assignements = assignements;
//    }
//
//    public List<Long> getExams() {
//        return exams;
//    }
//
//    public void setExams(List<Long> exams) {
//        this.exams = exams;
//    }
//
//    public Long getYearOfStudy() {
//        return yearOfStudy;
//    }
//
//    public void setYearOfStudy(Long yearOfStudy) {
//        this.yearOfStudy = yearOfStudy;
//    }
//
//    public Date getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(Date startDate) {
//        this.startDate = startDate;
//    }
//
//    public Date getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = endDate;
//    }
//
//    public Integer getMinimumCourseMaterials() {
//        return minimumCourseMaterials;
//    }
//
//    public void setMinimumCourseMaterials(Integer minimumCourseMaterials) {
//        this.minimumCourseMaterials = minimumCourseMaterials;
//    }
//
//    @Override
//    public String toString() {
//        return "CourseEntity{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", teacherID=" + teacherID +
//                ", materials=" + materials +
//                ", students=" + students +
//                ", assignements=" + assignements +
//                ", exams=" + exams +
//                ", yearOfStudy=" + yearOfStudy +
//                ", startDate=" + startDate +
//                ", endDate=" + endDate +
//                ", minimumCourseMaterials=" + minimumCourseMaterials +
//                '}';
//    }
//
//}

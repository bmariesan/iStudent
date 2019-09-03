package ro.ubb.istudent.dto;

/**
 * Created by dariusi on 1/26/18.
 */
public class CourseDTO implements Dto {

    private String name;
    private Integer completedAssignments;
    private Integer unfinishedAssignments;
    private String teacher;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getStarDate() {
        return starDate;
    }

    public void setStarDate(String starDate) {
        this.starDate = starDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    private String yearOfStudy;
    private String starDate;
    private String endDate;

    public CourseDTO(String name, Integer completedAssignments, Integer unfinishedAssignments)
    {
        this.name = name;
        this.completedAssignments = completedAssignments;
        this.unfinishedAssignments = unfinishedAssignments;
        teacher = "Igna Darius";
        yearOfStudy= "Year: 3 Semester: 2";
        starDate = "20.08.2017";
        endDate = "20.01.2018";
    }

    public Integer getUnfinishedAssignments() {
        return unfinishedAssignments;
    }

    public void setUnfinishedAssignments(Integer unfinishedAssignments) {
        this.unfinishedAssignments = unfinishedAssignments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getCompletedAssignments() {
        return completedAssignments;
    }

    public void setCompletedAssignments(Integer completedAssignments) {
        this.completedAssignments = completedAssignments;
    }
}

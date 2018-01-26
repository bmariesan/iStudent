package ro.ubb.istudent.dto;

/**
 * Created by dariusi on 1/26/18.
 */
public class CourseDTO implements Dto {

    private String name;
    private Integer completedAssignments;
    private Integer unfinishedAssignments;

    public CourseDTO(String name, Integer completedAssignments, Integer unfinishedAssignments)
    {
        this.name = name;
        this.completedAssignments = completedAssignments;
        this.unfinishedAssignments = unfinishedAssignments;
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

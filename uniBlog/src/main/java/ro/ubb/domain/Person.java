package ro.ubb.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tudorstanila on 14/12/2017.
 */
public abstract class Person implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private boolean isTeacher;
    private List<Course> courses;

    public Person(String id, String firstName, String lastName, boolean isTeacher, List<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isTeacher = isTeacher;
        this.courses = courses;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isTeacher=" + isTeacher +
                ", courses=" + courses +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

package ro.ubb.domain;

import java.util.List;

/**
 * Created by tudorstanila on 14/12/2017.
 */
public class Teacher extends Person {
    public Teacher(String id, String firstName, String lastName, boolean isTeacher, List<Course> courses) {
        super(id, firstName, lastName, isTeacher, courses);
    }
}

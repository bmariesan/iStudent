package ro.ubb.istudent.domain;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.ubb.istudent.exception.IllegalOperationException;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")
@Getter
@Setter
@ToString(exclude = {"registeredCourses"}, callSuper = true)
public class StudentEntity extends BaseEntity {
    @Indexed(unique = true)
    private String username;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "name", column = @Column(name = "name")) })
    private NameEntity name;

    @DBRef(lazy = true)
    private List<CourseEntity> registeredCourses;

    public List<CourseEntity> getRegisteredCourses() {
        if (registeredCourses == null) {
            return new ArrayList<>();
        }
        return registeredCourses;
    }

    public boolean isSubscribedToCourse(CourseEntity course) {
        for (CourseEntity registeredCourse : getRegisteredCourses()) {
            if (registeredCourse.getName().equals(course.getName())) {
                return true;
            }
        }
        return false;
    }

    public void subscribeToCourse(CourseEntity course) {
        if (isSubscribedToCourse(course)) {
            throw new IllegalOperationException("The student " + username + " is already subscribed " +
                    "to the course " + course);
        }

        List<CourseEntity> courses = getRegisteredCourses();
        courses.add(course);
        setRegisteredCourses(courses);
    }
}
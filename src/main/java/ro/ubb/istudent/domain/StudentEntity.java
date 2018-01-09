package ro.ubb.istudent.domain;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.ubb.istudent.exception.IllegalOperationException;

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

    private String name;

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
package ro.ubb.istudent.domain;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
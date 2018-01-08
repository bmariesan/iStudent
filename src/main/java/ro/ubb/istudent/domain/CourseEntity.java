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
@Document(collection = "courses")
@Getter
@Setter
@ToString(exclude = {"registeredStudents"}, callSuper = true)
public class CourseEntity extends BaseEntity {
    @Indexed(unique = true)
    private String name;

    private Integer studentLimit;

    @DBRef(lazy = true)
    private List<StudentEntity> registeredStudents;

    @DBRef
    private TeacherEntity teacher;

    public List<StudentEntity> getRegisteredStudents() {
        if (registeredStudents == null) {
            return new ArrayList<>();
        }
        return registeredStudents;
    }
}

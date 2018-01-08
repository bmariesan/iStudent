package ro.ubb.istudent.domain;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "teachers")
@Getter
@Setter
@ToString(exclude = {"courses"}, callSuper = true)
public class TeacherEntity extends BaseEntity {
    @Indexed(unique = true)
    private String name;

    @DBRef(lazy = true)
    private List<CourseEntity> courses;
}

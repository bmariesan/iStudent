package ro.ubb.istudent.domain;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

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
class TeacherEntity extends BaseEntity {
    @Indexed(unique = true)
    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "name", column = @Column(name = "name")) })
    private NameEntity name;

    @DBRef(lazy = true)
    private List<CourseEntity> courses;
}

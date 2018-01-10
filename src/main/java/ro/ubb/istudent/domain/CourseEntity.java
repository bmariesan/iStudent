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
public class CourseEntity extends BaseEntity {

    @Indexed(unique=true)
    private String name;

    private Integer minimumGrade;

    @DBRef
    private List<TestEntity> tests = new ArrayList<>();
}

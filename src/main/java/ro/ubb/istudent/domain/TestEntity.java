package ro.ubb.istudent.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tests")
public class TestEntity extends BaseEntity {
    @DBRef
    private CourseEntity course;

    private Integer grade;
}

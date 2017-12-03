package ro.ubb.istudent.domain;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.ubb.istudent.enums.Gender;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")
public class StudentEntity extends BaseEntity {

    @Indexed(unique=true)
    private String name;

    @Indexed
    private Gender gender;

    // We know that every year this value needs to be incremented and one day needs to be stopped
    // But as we use this entity as a mock we will assume that age is an Integer for easily manipulation
    private Integer age;

    private List<TestEntity> tests = new ArrayList<>();

    @DBRef
    private CountryEntity country;
}

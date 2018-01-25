package ro.ubb.istudent.grading.gradingbook;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Marius on 10.12.2017.
 */

@ToString
@Document(collection = "student")
@EqualsAndHashCode(callSuper = true)
public class Student extends UserBase {
    public Student(final String name) {
        super(ObjectId.get(), name, UserRole.STUDENT);
    }
}

package ro.ubb.istudent.grading.gradingbook.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Document(collection = "student")
@EqualsAndHashCode(callSuper = true)
public class NormalStudent extends UserBase implements Student {
    public NormalStudent(final String name) {
        super(ObjectId.get(), name);
    }
}

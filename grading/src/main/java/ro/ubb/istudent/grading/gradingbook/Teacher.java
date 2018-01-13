package ro.ubb.istudent.grading.gradingbook;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.concurrent.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by Marius on 10.12.2017.
 */
@ToString
@Immutable
@EqualsAndHashCode
@Document(collection = "teacher")
public class Teacher {

    @Id
    @JsonProperty
    private final ObjectId id;

    @JsonProperty
    private final String name;

    public Teacher(
            final ObjectId id,
            final String name
    ) {
        this.id = id;
        this.name = name;

    }

    public ObjectId id() {
        return id;
    }

    public String name() {
        return name;
    }

}
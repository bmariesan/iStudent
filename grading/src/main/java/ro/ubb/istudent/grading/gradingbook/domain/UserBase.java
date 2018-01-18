package ro.ubb.istudent.grading.gradingbook.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Marius on 10.12.2017.
 */

@ToString
@Document(collection = "user")
@EqualsAndHashCode(of = {"id"})
public class UserBase implements User {

    @Id
    @JsonProperty
    protected final ObjectId id;

    @JsonProperty
    protected final String name;

    public UserBase() {
        this("default-name");
    }

    public UserBase(final String name) {
        this(ObjectId.get(), name);
    }

    public UserBase(final ObjectId id, final String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public ObjectId id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }
}

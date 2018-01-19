package ro.ubb.istudent.grading.gradingbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Student.class, name = "@fromStudent"),
        @JsonSubTypes.Type(value = Teacher.class, name = "@teacher")})
public class UserBase implements User {

    @Id
    @JsonProperty
    protected final ObjectId id;

    @JsonProperty
    protected final String name;

    @JsonProperty
    protected final UserRole role;

    public UserBase() {
        this("default-name");
    }

    public UserBase(final String name) {
        this(ObjectId.get(), name);
    }

    public UserBase(final ObjectId id, final String name) {
        this(id, name, UserRole.USER);
    }

    public UserBase(ObjectId id, String name, UserRole role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    @Override
    public ObjectId id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public UserRole role() {
        return role;
    }
}

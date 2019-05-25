package ro.ubb.istudent.grading.gradingbook;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.bson.types.ObjectId;
import java.io.Serializable;

@SuppressWarnings("ALL")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "user")
@JsonSubTypes({@JsonSubTypes.Type(
        value = UserBase.class,
        name = "@user")})
public interface User extends Serializable {
    ObjectId id();
    String name();
    UserRole role();
}

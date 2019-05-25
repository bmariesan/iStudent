package ro.ubb.istudent.grading.gradingbook;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Optional;

/**
 * Created by Marius on 10.12.2017.
 */

@SuppressWarnings("unused")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "grade")
@JsonSubTypes({@JsonSubTypes.Type(
        value = SolidGrade.class,
        name = "solid-grade")})
public interface Grade extends Serializable {
    ObjectId id();
    Double value();
    Calendar created();
    Optional<User> student();
}

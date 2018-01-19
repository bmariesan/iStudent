package ro.ubb.istudent.grading.exam;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "exercise")
@JsonSubTypes({@JsonSubTypes.Type(
        value = CompletedExercise.class,
        name = "completed-exercise")})
public interface Exercise extends Serializable {
    ObjectId id();
    Double score();
    Question question();
    List<String> rightAnswers();
    List<String> rightAnswersFromUser();
}

package ro.ubb.istudent.grading.exam;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.List;
/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "question")
@JsonSubTypes({@JsonSubTypes.Type(
        value = ChoiceQuestion.class,
        name = "choice-question")})
public interface Question extends Serializable {
    List<String> answers();
    Boolean isCorrect(final List<String> answers);
    Double points();
}

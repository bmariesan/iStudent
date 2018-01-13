package ro.ubb.istudent.grading.exam;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

public interface Exercise extends Serializable {
    Double getScore();
    Question getQuestion();
    List<String> getUserAnswers();
    Boolean isRight();
    ObjectId getId();
}

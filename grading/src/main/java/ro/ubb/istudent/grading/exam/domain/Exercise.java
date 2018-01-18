package ro.ubb.istudent.grading.exam.domain;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alexandru Arnautu
 * @version 1.0
 */

public interface Exercise extends Serializable {
    ObjectId id();
    Double score();
    Question question();
    List<String> rightAnswers();
    List<String> rightAnswersFromUser();
}

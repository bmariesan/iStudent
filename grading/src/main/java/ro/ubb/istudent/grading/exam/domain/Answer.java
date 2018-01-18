package ro.ubb.istudent.grading.exam.domain;

import org.bson.types.ObjectId;

import java.util.List;

public interface Answer {
    ObjectId exerciseId();
    List<String> rightAnswersFromUserInExercise();
    List<String> allRightAnswersFromExercise();
}

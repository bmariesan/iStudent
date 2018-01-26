package ro.ubb.istudent.grading.exam;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ro.ubb.istudent.grading.gradingbook.User;

import java.util.List;
import java.util.Optional;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "@completed-work-unit")
@JsonSubTypes({@JsonSubTypes.Type(
        value = UnitOfWorkWithCompletedExercises.class,
        name = "completed-work-unit")})
public interface CompletedUnitOfWork extends UnitOfWork {
    Double totalScore();
    List<Exercise> correctExercises();
    List<Exercise> partiallyCorrectExercises();
    Optional<User> fromStudent();
    User student();
}

package ro.ubb.istudent.grading.service;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.grading.domain.CompletedExercise;
import ro.ubb.istudent.grading.repository.ChoiceQuestionRepository;
import ro.ubb.istudent.grading.repository.CompletedExecrciseRepository;

/**
 * @author Alexandru Arnautu
 */

@Service
@Immutable
public class CompletedExerciseService
{

    @Autowired
    private final CompletedExecrciseRepository completedExecrciseRepository;

    public CompletedExerciseService(final CompletedExecrciseRepository completedExecrciseRepository ) {
        this.completedExecrciseRepository= completedExecrciseRepository;
    }

    public CompletedExercise insert(final CompletedExercise completedExercise) {
        return completedExecrciseRepository.save(completedExercise);
    }

    public void delete(final ObjectId choiceQuestionId) {
        completedExecrciseRepository.delete(choiceQuestionId);
    }

    public CompletedExercise update(final CompletedExercise completedExercise) {
        return completedExecrciseRepository.save(completedExercise);
    }

    public CompletedExercise getCompletedExerciseById(final ObjectId completedExerciseId) {
        return completedExecrciseRepository.findOne(completedExerciseId);
    }

    private CompletedExercise save(final CompletedExercise criteria) {
        return completedExecrciseRepository.save(criteria);
    }
}

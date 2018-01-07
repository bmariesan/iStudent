package ro.ubb.istudent.grading.service;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.grading.domain.NotCompletedExercise;
import ro.ubb.istudent.grading.repository.NotCompletedExerciseRepository;

/**
 * @author Alexandru Arnautu
 */

@Service
@Immutable
public class NotCompletedExerciseService
{
    @Autowired
    private final NotCompletedExerciseRepository notCompletedExerciseRepository;

    public NotCompletedExerciseService(final NotCompletedExerciseRepository notCompletedExerciseRepository) {
        this.notCompletedExerciseRepository = notCompletedExerciseRepository;
    }

    public NotCompletedExercise insert(final NotCompletedExercise notCompletedExercise) {
        return notCompletedExerciseRepository.save(notCompletedExercise);
    }

    public void delete(final ObjectId notCompletedExerciseId) {
        notCompletedExerciseRepository.delete(notCompletedExerciseId);
    }

    public NotCompletedExercise update(final NotCompletedExercise notCompletedExercise) {
        return notCompletedExerciseRepository.save(notCompletedExercise);
    }

    public NotCompletedExercise getNotCompletedExerciseById(final ObjectId notCompletedExerciseId) {
        return notCompletedExerciseRepository.findOne(notCompletedExerciseId);
    }

    private NotCompletedExercise save(final NotCompletedExercise criteria) {
        return notCompletedExerciseRepository.save(criteria);
    }
}

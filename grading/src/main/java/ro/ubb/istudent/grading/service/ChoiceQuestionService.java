package ro.ubb.istudent.grading.service;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.course.CourseRepository;
import ro.ubb.istudent.grading.criteria.*;
import ro.ubb.istudent.grading.domain.ChoiceQuestion;
import ro.ubb.istudent.grading.repository.ChoiceQuestionRepository;

import java.awt.*;

/**
 * @author Alexandru Arnautu
 */

@Service
@Immutable
public class ChoiceQuestionService
{

    @Autowired
    private final ChoiceQuestionRepository choiceQuestionRepository;

    public ChoiceQuestionService(final ChoiceQuestionRepository choiceQuestionRepository) {
        this.choiceQuestionRepository = choiceQuestionRepository;
    }

    public ChoiceQuestion insert(final ChoiceQuestion choiceQuestion) {
        return choiceQuestionRepository.save(choiceQuestion);
    }

    public void delete(final ObjectId choiceQuestionId) {
        choiceQuestionRepository.delete(choiceQuestionId);
    }

    public ChoiceQuestion update(final ChoiceQuestion choiceQuestion) {
        return choiceQuestionRepository.save(choiceQuestion);
    }

    public ChoiceQuestion getChoiceQuestionById(final ObjectId choiceQuestionId) {
        return choiceQuestionRepository.findOne(choiceQuestionId);
    }

    private ChoiceQuestion save(final ChoiceQuestion criteria) {
        return choiceQuestionRepository.save(criteria);
    }
}

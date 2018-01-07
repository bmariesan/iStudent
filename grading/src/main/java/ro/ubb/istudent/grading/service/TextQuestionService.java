package ro.ubb.istudent.grading.service;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.grading.domain.TextQuestion;
import ro.ubb.istudent.grading.repository.TextQuestionRepository;
import ro.ubb.istudent.grading.repository.TextQuestionRepository;

/**
 * @author Alexandru Arnautu
 */

@Service
@Immutable
public class TextQuestionService
{
    @Autowired
    private final TextQuestionRepository textQuestionRepository;

    public TextQuestionService(final TextQuestionRepository textQuestionRepository) {
        this.textQuestionRepository = textQuestionRepository;
    }

    public TextQuestion insert(final TextQuestion textQuestion) {
        return textQuestionRepository.save(textQuestion);
    }

    public void delete(final ObjectId textQuestionId) {
        textQuestionRepository.delete(textQuestionId);
    }

    public TextQuestion update(final TextQuestion textQuestion) {
        return textQuestionRepository.save(textQuestion);
    }

    public TextQuestion getTextQuestionById(final ObjectId textQuestionId) {
        return textQuestionRepository.findOne(textQuestionId);
    }

    private TextQuestion save(final TextQuestion criteria) {
        return textQuestionRepository.save(criteria);
    }
}

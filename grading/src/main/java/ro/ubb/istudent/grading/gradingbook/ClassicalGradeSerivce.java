package ro.ubb.istudent.grading.gradingbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.grading.domain.Grade;

/**
 * Created by Marius on 10.12.2017.
 */
public class ClassicalGradeSerivce extends GradeServiceBase {

    @Autowired
    GradingRepository repo;

    @Override
    public Iterable<Grade> getGrades() {
        return repo.findAll();
    }
}

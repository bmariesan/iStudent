package ro.ubb.istudent.grading.gradingbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.grading.domain.Grade;
import ro.ubb.istudent.grading.domain.GradingBook;

import java.util.Date;
import java.util.Enumeration;

/**
 * Created by Marius on 10.12.2017.
 */


public class ArchivedGradeService extends GradeServiceBase {

    @Autowired
    GradingRepository repo;

    @Override
    public Iterable<Grade> getGrades() {
        Query q = new Query();
        q.addCriteria(Criteria.where("expiry").gt(new Date()));

        // TODO: filter
        return repo.findAll();
    }
}

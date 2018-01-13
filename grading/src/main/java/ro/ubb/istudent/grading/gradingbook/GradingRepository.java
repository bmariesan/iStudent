package ro.ubb.istudent.grading.gradingbook;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.grading.domain.Grade;

public interface GradingRepository extends MongoRepository<Grade, String> {
}

package ro.ubb.istudent.grading.gradingbook;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.grading.domain.Grade;
import ro.ubb.istudent.grading.domain.GradingBook;

interface GradingBookRepository extends MongoRepository<GradingBook, String> {

}
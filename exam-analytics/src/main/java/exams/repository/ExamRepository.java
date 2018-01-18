package exams.repository;

import exams.domain.statistics.IStatistic;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamRepository extends MongoRepository<IStatistic, ObjectId> {
    

}

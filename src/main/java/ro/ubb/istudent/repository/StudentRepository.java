package ro.ubb.istudent.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.domain.IStatistic;

public interface StudentRepository extends MongoRepository<IStatistic,ObjectId>{

}
